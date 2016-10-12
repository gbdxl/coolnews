package com.xianrou.zhihudaily.presenter.contractor;

import com.xianrou.zhihudaily.base.BasePresenter;
import com.xianrou.zhihudaily.base.BaseView;
import com.xianrou.zhihudaily.bean.WelcomeBean;

/**
 * Created by 磊.
 * Date 2016/10/12 11:31
 */

public class SplashConractor {

	public interface View extends BaseView {
		void showPic(WelcomeBean welcomeBean);
		void jumpToMain();
	}

	public interface Presenter extends BasePresenter<View> {
		void getPic();
	}

}
