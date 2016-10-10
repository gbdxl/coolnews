package com.xianrou.zhihudaily.presenter;

import android.util.Log;

import com.xianrou.zhihudaily.base.BasePresenterImpl;
import com.xianrou.zhihudaily.http.RetrofitHelper;
import com.xianrou.zhihudaily.presenter.contractor.DailyContractor;
import com.xianrou.zhihudaily.uitls.RxUtil;

import rx.Subscription;

/**
 * Created by android studio.
 * user 磊
 * Date 2016/9/27
 * Time 11:29
 * Desc
 */

public class DailyPresenter extends BasePresenterImpl<DailyContractor.View>
		implements DailyContractor.Presenter {

	private final RetrofitHelper mRetrofitHelper;

	public DailyPresenter() {
		mRetrofitHelper = new RetrofitHelper();
	}

	@Override
	public void getData() {
		Subscription rxSubscription = mRetrofitHelper.fetchDailyListInfo()
				.compose(RxUtil.rxSchedulerHelper())
				.subscribe(dailyListBean -> {
					mView.showContent(dailyListBean);
					mView.hideRefresh();
				}, throwable -> {
					mView.toast("数据加载失败ヽ(≧Д≦)ノ");
					mView.hideRefresh();
					Log.e("DailyPresenter", throwable.toString());
				});
		addSubscribe(rxSubscription);
	}

	@Override
	public void getMoreData() {

	}
}
