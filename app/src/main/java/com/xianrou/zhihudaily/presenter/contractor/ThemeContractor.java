package com.xianrou.zhihudaily.presenter.contractor;

import com.xianrou.zhihudaily.base.BasePresenter;
import com.xianrou.zhihudaily.base.BaseView;
import com.xianrou.zhihudaily.bean.ThemeListBean;

/**
 * Created by android studio.
 * user 磊
 * Date 2016/10/10
 * Time 15:37
 * Desc
 */

public class ThemeContractor {

	public interface View extends BaseView {

		void showContent(ThemeListBean bean);

		void hideRefresh();

	}

	public interface Presenter extends BasePresenter<View> {

		void getData();
	}

}
