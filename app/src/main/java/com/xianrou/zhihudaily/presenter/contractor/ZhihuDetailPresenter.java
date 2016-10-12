package com.xianrou.zhihudaily.presenter.contractor;

import com.xianrou.zhihudaily.R;
import com.xianrou.zhihudaily.base.BasePresenterImpl;
import com.xianrou.zhihudaily.http.RetrofitHelper;
import com.xianrou.zhihudaily.uitls.RxUtil;
import com.xianrou.zhihudaily.uitls.TLog;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by 磊.
 * Date 2016/10/11 18:51
 */

public class ZhihuDetailPresenter extends BasePresenterImpl<ZhihuDetailContractor.View>
		implements ZhihuDetailContractor.Presenter {

	private final RetrofitHelper mRetrofitHelper;

	public ZhihuDetailPresenter() {
		mRetrofitHelper = new RetrofitHelper();
	}

	@Override
	public void getDetailData(int id) {
		Subscription subscribe = mRetrofitHelper.fetchDetailInfo(id)
				.compose(RxUtil.rxSchedulerHelper())
				.doOnSubscribe(() -> mView.showLoadingView())
				.subscribeOn(AndroidSchedulers.mainThread())
				.subscribe(detailBean -> {
					mView.showContent(detailBean);
					mView.hideLoadingView();
				}, throwable -> {
					mView.toast(R.string.no_more_data);
				});
		addSubscribe(subscribe);
	}

	@Override
	public void getExtraData(int id) {
		Subscription subscribe = mRetrofitHelper.fetchDetailExtraInfo(id)
				.compose(RxUtil.rxSchedulerHelper())
				.subscribe(extraBean -> mView.showExtraData(extraBean),
						throwable -> TLog.e("获取评论数量失败"));
		addSubscribe(subscribe);
	}
}
