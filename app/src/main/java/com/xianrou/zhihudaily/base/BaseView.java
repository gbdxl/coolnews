package com.xianrou.zhihudaily.base;

/**
 * Created by lei on 2016/9/8.
 */
public interface BaseView {

	void toast(String msg);

	void toast(int resid);

	void showLoadingView();

	void hideLoadingView();
}
