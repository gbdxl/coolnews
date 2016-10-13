package com.xianrou.zhihudaily.presenter;

import com.xianrou.zhihudaily.R;
import com.xianrou.zhihudaily.base.BasePresenterImpl;
import com.xianrou.zhihudaily.bean.ReadBean;
import com.xianrou.zhihudaily.bean.RecentBean;
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
				.map(listBean -> {
					for (RecentBean bean : listBean.recent) {
						bean.readState = mRealmHelper.queryReadNews(bean.news_id);
					}
					return listBean;
				})
				.subscribe(listBean -> {
					mView.showContent(listBean);
					mView.hideLoadingView();
				}, throwable -> {
					mView.toast(R.string.no_more_data);
					mView.hideLoadingView();
				});
		addSubscribe(subscribe);
	}

	@Override
	public void insertReadData(ReadBean bean, int position) {
		mRealmHelper.insertReadNews(bean);
		mView.updateReadUi(position);
	}
}
