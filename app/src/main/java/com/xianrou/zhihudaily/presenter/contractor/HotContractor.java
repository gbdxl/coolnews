package com.xianrou.zhihudaily.presenter.contractor;

import com.xianrou.zhihudaily.base.BasePresenter;
import com.xianrou.zhihudaily.base.BaseView;
import com.xianrou.zhihudaily.bean.HotListBean;

/**
 * Created by 磊.
 * Date 2016/10/12 17:18
 */

public class HotContractor {

	public interface View extends BaseView {
		void showContent(HotListBean listBean);
	}

	public interface Presenter extends BasePresenter<View> {
		void getData();
	}
}
