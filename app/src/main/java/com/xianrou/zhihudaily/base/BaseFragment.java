package com.xianrou.zhihudaily.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xianrou.zhihudaily.uitls.NullCheckUtil;
import com.xianrou.zhihudaily.uitls.ToastUtil;

import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportFragment;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;


/**
 * Created by lei on 2016/9/9.
 */
public abstract class BaseFragment<T extends BasePresenter> extends SupportFragment implements BaseView {

	protected T mPresenter = null;

	private static final String STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN";
	/**
	 * Log tag
	 */
	protected static String TAG_LOG = null;

	/**
	 * Screen information
	 */
	protected int mScreenWidth = 0;
	protected int mScreenHeight = 0;
	protected float mScreenDensity = 0.0f;

	/**
	 * Activity
	 */
	protected Activity mActivity = null;
	/**
	 * context
	 */
	protected Context mContext = null;
	private CompositeSubscription mCompositeSubscription;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mActivity = getActivity();
		mContext = getContext();
		TAG_LOG = this.getClass().getSimpleName();
//		setFragmentNotOverLap(savedInstanceState);
	}

	/**
	 * 解决fragment重叠
	 * @param savedInstanceState
	 */
	private void setFragmentNotOverLap(Bundle savedInstanceState) {
		if (savedInstanceState != null) {
			boolean isSupportHidden = savedInstanceState.getBoolean(STATE_SAVE_IS_HIDDEN);

			FragmentTransaction ft = getFragmentManager().beginTransaction();
			if (isSupportHidden) {
				ft.hide(this);
			} else {
				ft.show(this);
			}
			ft.commit();
		}
	}

	/**
	 * bind layout resource file
	 *
	 * @return id of layout resource
	 */
	protected abstract int getContentViewLayoutID();

	@Override
	public void onSaveInstanceState(Bundle outState) {
		outState.putBoolean(STATE_SAVE_IS_HIDDEN, isHidden());
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		int layout_id = getContentViewLayoutID();
		if (layout_id != 0) {
			View view = inflater.inflate(layout_id, null);
			ButterKnife.bind(this,view);
			initViews(view);
			if (mPresenter != null)
				mPresenter.attachView(this);
			initData();
			return view;
		} else
			throw new IllegalArgumentException("You must return a right target layout id for inflater");
	}

	protected abstract void initData();

	protected abstract void initViews(View view);

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		initializeScreenValue();
	}

	/**
	 * initialize Screen width height density
	 */
	protected void initializeScreenValue() {
		DisplayMetrics displayMetrics = new DisplayMetrics();
		mActivity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

		mScreenDensity = displayMetrics.density;
		mScreenHeight = displayMetrics.heightPixels;
		mScreenWidth = displayMetrics.widthPixels;
	}

	@Override
	public void toast(String msg) {
		if (!TextUtils.isEmpty(msg)) {
			ToastUtil.showToast(msg);
		}
	}

	@Override
	public void toast(int resId) {
		if (resId != 0) {
			ToastUtil.showToast(resId);
		}
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

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (null != mPresenter)
			mPresenter.detachView();
		unSubscribe();
	}
}
