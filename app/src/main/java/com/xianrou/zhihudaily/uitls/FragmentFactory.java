package com.xianrou.zhihudaily.uitls;

import android.util.SparseArray;

import com.xianrou.zhihudaily.base.BaseFragment;
import com.xianrou.zhihudaily.ui.function.FavoriteFragment;
import com.xianrou.zhihudaily.ui.main.ZhihuFragment;

/**
 * Created by 磊.
 * Date 2016/10/14 10:14
 */

public class FragmentFactory {

	private static FragmentFactory sInstance;

	private SparseArray<BaseFragment> mSparseArray = new SparseArray<>();

	public static final int ZHIHU = 0;
	public static final int WEIXIN = 1;
	public static final int WANGYI = 2;
	public static final int FAVORITY = 3;

	private FragmentFactory() {}

	public static FragmentFactory getInstance() {
		if (sInstance == null) {
			synchronized (FragmentFactory.class) {
				if (sInstance == null) {
					return new FragmentFactory();
				}
			}
		}
		return sInstance;
	}

	public BaseFragment getFragment(int type) {
		BaseFragment fragment = mSparseArray.get(type);
		if (null != fragment) {
			return fragment;
		}
		switch (type) {
			case ZHIHU:
				fragment = new ZhihuFragment();
				break;
			case WEIXIN:

				break;
			case WANGYI:

				break;
			case FAVORITY:
				fragment = new FavoriteFragment();
				break;
		}
		mSparseArray.append(type,fragment);
		return fragment;
	}

}
