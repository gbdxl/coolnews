package com.xianrou.zhihudaily.ui.main.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by android studio.
 * user 磊
 * Date 2016/9/26
 * Time 18:05
 * Desc
 */

public class ZhihuMainAdapter extends FragmentPagerAdapter {

	private List<Fragment> fragments;

	private String[] titles;
	public ZhihuMainAdapter(FragmentManager fm, List<Fragment> fragments,String[] titles) {
		super(fm);
		this.fragments = fragments;
		this.titles = titles;
	}

	@Override
	public Fragment getItem(int position) {
		return fragments.get(position);
	}

	@Override
	public int getCount() {
		return fragments.size();
	}

	public int getItemPosition(Object object) {
		return PagerAdapter.POSITION_NONE;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return titles[position];
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
	}
}
