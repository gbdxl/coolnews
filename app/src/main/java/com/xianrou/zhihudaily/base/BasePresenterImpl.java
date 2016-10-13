package com.xianrou.zhihudaily.base;


import com.xianrou.zhihudaily.http.RetrofitHelper;
import com.xianrou.zhihudaily.uitls.NullCheckUtil;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by lei on 2016/9/8.
 */
public class BasePresenterImpl<T extends BaseView>
		implements BasePresenter<T> {

	private CompositeSubscription mCompositeSubscription;

	protected T mView;

	protected RetrofitHelper mRetrofitHelper;
//	protected RealmHelper mRealmHelper;

	public BasePresenterImpl() {
		mRetrofitHelper = new RetrofitHelper();
//		mRealmHelper = new RealmHelper();
	}

	protected void addSubscribe(Subscription subscription) {
		NullCheckUtil.checkNotNull(subscription, "subscription 不能为空");
		if (mCompositeSubscription == null) {
			mCompositeSubscription = new CompositeSubscription();
		}
		mCompositeSubscription.add(subscription);
	}

	private void unSubscibe() {
		if (null != mCompositeSubscription) {
			mCompositeSubscription.unsubscribe();
		}
	}

	@Override
	public void attachView(T view) {
		mView = view;
	}

	@Override
	public void detachView() {
		if (null != mView)
			mView = null;
		unSubscibe();
	}
}
