package com.xianrou.zhihudaily.presenter;

import com.xianrou.zhihudaily.R;
import com.xianrou.zhihudaily.base.BasePresenterImpl;
import com.xianrou.zhihudaily.http.RetrofitHelper;
import com.xianrou.zhihudaily.presenter.contractor.SectionContractor;
import com.xianrou.zhihudaily.uitls.RxUtil;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by 磊.
 * Date 2016/10/12 16:09
 */

public class SectionPresenter extends BasePresenterImpl<SectionContractor.View>
		implements SectionContractor.Presenter {

	private final RetrofitHelper mRetrofitHelper;

	public SectionPresenter() {
		mRetrofitHelper = new RetrofitHelper();
	}

	@Override
	public void getData() {
		Subscription subscribe = mRetrofitHelper.fetchSectionListInfo()
				.compose(RxUtil.rxSchedulerHelper())
				.doOnSubscribe(() -> mView.showLoadingView())
				.subscribeOn(AndroidSchedulers.mainThread())
				.subscribe(beanList -> {
					mView.hideLoadingView();
					mView.showContent(beanList);
				}, throwable -> {
					mView.toast(R.string.no_more_data);
					mView.hideLoadingView();
				});
		addSubscribe(subscribe);
	}
}
