package com.xianrou.zhihudaily.base;

/**
 * Created by lei on 2016/9/8.
 */
public interface BasePresenter<T extends BaseView> {

	void attachView(T view);

	void detachView();
}
