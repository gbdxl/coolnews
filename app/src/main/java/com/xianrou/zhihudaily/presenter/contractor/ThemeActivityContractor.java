package com.xianrou.zhihudaily.presenter.contractor;

import com.xianrou.zhihudaily.base.BasePresenter;
import com.xianrou.zhihudaily.base.BaseView;
import com.xianrou.zhihudaily.bean.ThemeChildListBean;

/**
 * Created by android studio.
 * user 磊
 * Date 2016/10/10
 * Time 18:15
 * Desc
 */

public class ThemeActivityContractor {

	public interface View extends BaseView {
		void showContent(ThemeChildListBean bean);
	}

	public interface Presenter extends BasePresenter<View> {
		void getData(int id);
	}
}
