package com.xianrou.zhihudaily.base;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

import com.xianrou.zhihudaily.R;
import com.xianrou.zhihudaily.uitls.NullCheckUtil;
import com.xianrou.zhihudaily.uitls.ToastUtil;

import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportActivity;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by lei on 2016/9/8.
 */
public abstract class BaseActivity<T extends BasePresenter>
		extends SupportActivity implements BaseView {

	protected T mPresenter;
	private static Class classz;
	protected static String LOG_TAG = "";

	protected Toolbar mToolbar;

	protected Resources mResources;

	protected Bundle mSavedInstanceState;

	protected FragmentManager mFragmentManager;

	protected Context mContext;

	/**
	 * Screen information
	 */
	protected int mScreenWidth = 0;
	protected int mScreenHeight = 0;
	protected float mScreenDensity = 0.0f;
	private CompositeSubscription mCompositeSubscription;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setStatusBar();
		mSavedInstanceState = savedInstanceState;

		mFragmentManager = getSupportFragmentManager();
		mContext = this;
		classz = this.getClass();
		LOG_TAG = classz.getSimpleName();
		mResources = getResources();
		initializeScreenValue();


		if (getContentId() != 0)
			setContentView(getContentId());

		if (mSavedInstanceState == null)
			loadFragment(0, null);

		mToolbar = ButterKnife.findById(this, R.id.toolbar);
		if (null != mToolbar) {
			setSupportActionBar(mToolbar);
			getSupportActionBar().setHomeButtonEnabled(true);
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
			getSupportActionBar().setDisplayShowTitleEnabled(false);
		}

		initViews();
		initData();
		if (mPresenter != null)
			mPresenter.attachView(this);
	}

	private void setStatusBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以上
			View decorView = getWindow().getDecorView();
			int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
					| View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
			decorView.setSystemUiVisibility(option);
			getWindow().setStatusBarColor(Color.TRANSPARENT);
		} else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4到5.0
			WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
			localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
		}
	}

	protected void loadFragment(int containerId, BaseFragment fragment) {
		if (containerId != 0 && fragment != null)
			loadRootFragment(containerId, fragment);
	}

	@Override
	public void setContentView(int layoutResID) {
		super.setContentView(layoutResID);
		ButterKnife.bind(this);
	}

	/**
	 * iniitialize Screen width heigth density
	 */
	protected void initializeScreenValue() {
		DisplayMetrics displayMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

		mScreenDensity = displayMetrics.density;
		mScreenHeight = displayMetrics.heightPixels;
		mScreenWidth = displayMetrics.widthPixels;
	}

	protected abstract int getContentId();

	protected abstract void initViews();

	protected abstract void initData();

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (mPresenter != null) {
			mPresenter.detachView();
		}
		unSubscribe();
	}

	@Override
	public void toast(String msg) {
		if(!TextUtils.isEmpty(msg))
			ToastUtil.showToast(msg);
	}

	@Override
	public void toast(int resId) {
		if (resId != 0)
			ToastUtil.showToast(resId);
	}

	@Override
	public void showLoadingView() {

	}

	@Override
	public void hideLoadingView() {

	}
	protected void addSubscribe(Subscription subscription) {
		NullCheckUtil.checkNotNull(subscription, "subscription 不能为空");
		if (mCompositeSubscription == null) {
			mCompositeSubscription = new CompositeSubscription();
		}
		mCompositeSubscription.add(subscription);
	}

	private void unSubscribe() {
		if (null != mCompositeSubscription) {
			mCompositeSubscription.unsubscribe();
		}
	}

}
