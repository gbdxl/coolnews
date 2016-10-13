package com.xianrou.zhihudaily.presenter;

import com.xianrou.zhihudaily.base.BasePresenterImpl;
import com.xianrou.zhihudaily.presenter.contractor.SplashConractor;
import com.xianrou.zhihudaily.uitls.RxUtil;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;

/**
 * Created by 磊.
 * Date 2016/10/12 11:35
 */

public class SplashPresenter extends BasePresenterImpl<SplashConractor.View> implements SplashConractor.Presenter {

	private static final String RES = "1080*1776";

	@Override
	public void getPic() {

		Subscription subscribe = mRetrofitHelper.fetchWelcomeInfo(RES)
				.compose(RxUtil.rxSchedulerHelper())
				.subscribe(welcomeBean -> {
					mView.showPic(welcomeBean);
					jumpToMain();
				}, throwable -> {
					mView.jumpToMain();
				});
		addSubscribe(subscribe);
	}

	private void jumpToMain() {
		Subscription subscribe = Observable.timer(2200, TimeUnit.MILLISECONDS)
				.subscribe(aLong -> {
					mView.jumpToMain();
				});
		addSubscribe(subscribe);
	}
}
