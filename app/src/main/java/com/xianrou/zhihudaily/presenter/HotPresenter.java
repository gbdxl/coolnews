package com.xianrou.zhihudaily.presenter;

import com.xianrou.zhihudaily.R;
import com.xianrou.zhihudaily.base.BasePresenterImpl;
import com.xianrou.zhihudaily.presenter.contractor.HotContractor;
import com.xianrou.zhihudaily.uitls.RxUtil;

import rx.Subscription;

/**
 * Created by 磊.
 * Date 2016/10/12 17:20
 */

public class HotPresenter extends BasePresenterImpl<HotContractor.View> implements HotContractor.Presenter {

	@Override
	public void getData() {
		Subscription subscribe = mRetrofitHelper.fetchHotListInfo()
				.compose(RxUtil.rxSchedulerHelper())
				.subscribe(listBean -> {
					mView.showContent(listBean);
					mView.hideLoadingView();
				}, throwable -> {
					mView.toast(R.string.no_more_data);
					mView.hideLoadingView();
				});
		addSubscribe(subscribe);
	}
}
