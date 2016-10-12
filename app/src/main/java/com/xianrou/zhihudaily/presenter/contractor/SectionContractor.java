package com.xianrou.zhihudaily.presenter.contractor;

import com.xianrou.zhihudaily.base.BasePresenter;
import com.xianrou.zhihudaily.base.BaseView;
import com.xianrou.zhihudaily.bean.SectionListBean;

/**
 * Created by 磊.
 * Date 2016/10/12 16:04
 */

public class SectionContractor {

	public interface View extends BaseView {
		void showContent(SectionListBean beanList);
	}

	public interface Presenter extends BasePresenter<View> {

		void getData();
	}

}
