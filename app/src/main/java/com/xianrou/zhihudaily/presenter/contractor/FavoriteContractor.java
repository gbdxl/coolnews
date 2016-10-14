package com.xianrou.zhihudaily.presenter.contractor;

import com.xianrou.zhihudaily.base.BasePresenter;
import com.xianrou.zhihudaily.base.BaseView;
import com.xianrou.zhihudaily.bean.ReadBean;

import java.util.List;

/**
 * Created by 磊.
 * Date 2016/10/14 10:55
 */

public class FavoriteContractor {

	public interface View extends BaseView {
		void showContent(List<ReadBean> list);
	}

	public interface Presenter extends BasePresenter<View> {
		void getData();
	}
}
