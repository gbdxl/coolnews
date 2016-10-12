package com.xianrou.zhihudaily.presenter.contractor;

import com.xianrou.zhihudaily.base.BasePresenter;
import com.xianrou.zhihudaily.base.BaseView;
import com.xianrou.zhihudaily.bean.ZhihuDetailBean;

/**
 * Created by 磊.
 * Date 2016/10/11 18:46
 */

public class ZhihuDetailContractor {

	public interface View extends BaseView {
		void showContent(ZhihuDetailBean detailBean);
	}

	public interface Presenter extends BasePresenter<View> {
		void getDetailData(int id);
	}
}
