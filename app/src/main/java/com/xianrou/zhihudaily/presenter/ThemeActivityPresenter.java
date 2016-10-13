package com.xianrou.zhihudaily.presenter;

import com.xianrou.zhihudaily.base.BasePresenterImpl;
import com.xianrou.zhihudaily.presenter.contractor.ThemeActivityContractor;
import com.xianrou.zhihudaily.uitls.RxUtil;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by android studio.
 * user 磊
 * Date 2016/10/10
 * Time 18:19
 * Desc
 */

public class ThemeActivityPresenter extends BasePresenterImpl<ThemeActivityContractor.View>
		implements ThemeActivityContractor.Presenter {

	@Override
	public void getData(int id) {
		Subscription subscribe = mRetrofitHelper.fetchThemeChildListInfo(id)
				.compose(RxUtil.rxSchedulerHelper())
				.doOnSubscribe(() -> mView.showLoadingView())
				.subscribeOn(AndroidSchedulers.mainThread())
				.subscribe(themeChildListBean -> {
					mView.showContent(themeChildListBean);
					mView.hideLoadingView();
				}, throwable -> {
					mView.hideLoadingView();
					mView.toast("数据加载失败~~(╯﹏╰)b");
				});
		addSubscribe(subscribe);
	}
}
