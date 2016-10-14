package com.xianrou.zhihudaily.presenter;

import com.xianrou.zhihudaily.base.BasePresenterImpl;
import com.xianrou.zhihudaily.bean.ReadBean;
import com.xianrou.zhihudaily.presenter.contractor.FavoriteContractor;

import io.realm.RealmResults;

/**
 * Created by 磊.
 * Date 2016/10/14 10:58
 */

public class FavoritePresenter extends BasePresenterImpl<FavoriteContractor.View>
		implements FavoriteContractor.Presenter {

	@Override
	public void getData() {
		RealmResults<ReadBean> allReadBeen = mRealmHelper.getAllReadBeen();
		if (allReadBeen.size() == 0) {
			mView.showLoadingView();
		} else {
			mView.showContent(allReadBeen);
		}
	}
}
