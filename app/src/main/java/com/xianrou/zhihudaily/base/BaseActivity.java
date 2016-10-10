package com.xianrou.zhihudaily.base;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;

import com.xianrou.zhihudaily.R;
import com.xianrou.zhihudaily.uitls.ToastUtil;

import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportActivity;

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

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
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


	protected abstract void initViews();

	protected abstract void initData();

	protected abstract int getContentId();

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (mPresenter != null) {
			mPresenter.detachView();
		}
	}

	@Override
	public void toast(String msg) {
		ToastUtil.showToast(msg);
	}

	@Override
	public void showLoadingView() {

	}

	@Override
	public void hideLoadingView() {

	}


}
