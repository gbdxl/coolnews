package com.xianrou.zhihudaily.presenter;

import android.util.Log;

import com.xianrou.zhihudaily.R;
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
					mView.toast(R.string.no_more_data);
					mView.hideRefresh();
					Log.e("DailyPresenter", throwable.toString());
				});
		addSubscribe(rxSubscription);
	}

	@Override
	public void getMoreData(String date) {
		String newDate = changeDate(date);
		Subscription subscribe = mRetrofitHelper.fetchDailyBeforeListInfo(newDate)
				.compose(RxUtil.rxSchedulerHelper())
				.subscribe(listBean -> {
					if (listBean == null || listBean.stories.size() == 0)
						mView.loadComplete();
					else
						mView.showMoreContent(listBean);
				}, throwable -> {
					mView.toast(R.string.no_more_data);
					mView.loadComplete();
				});
		addSubscribe(subscribe);
	}

	private String changeDate(String date) {
		int year = Integer.valueOf(date.substring(0,4));
		int month = Integer.valueOf(date.substring(4,6));
		int day = Integer.valueOf(date.substring(6,8));
		String monthStr = "";
		String dayStr = "";
		if (month < 10)
			monthStr = "0" + month;
		else
			monthStr = String.valueOf(month);
		if (day < 10)
			dayStr = "0" + day;
		else
			dayStr = String.valueOf(day);
		return year + monthStr + dayStr;
	}
}
