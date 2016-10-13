package com.xianrou.zhihudaily.presenter;

import com.xianrou.zhihudaily.base.BasePresenterImpl;
import com.xianrou.zhihudaily.presenter.contractor.ThemeContractor;
import com.xianrou.zhihudaily.uitls.RxUtil;
import com.xianrou.zhihudaily.uitls.TLog;

import rx.Subscription;

/**
 * Created by android studio.
 * user 磊
 * Date 2016/10/10
 * Time 15:40
 * Desc
 */

public class ThemePresenter extends BasePresenterImpl<ThemeContractor.View> implements ThemeContractor.Presenter {

	@Override
	public void getData() {
		Subscription subscribe = mRetrofitHelper.fetchDailyThemeListInfo()
				.compose(RxUtil.rxSchedulerHelper())
				.subscribe(data -> {
					mView.showContent(data);
					mView.hideRefresh();
					TLog.d(data.toString());
				}, throwable -> {
					mView.hideRefresh();
					TLog.e(throwable);
				});
		addSubscribe(subscribe);
	}

}
