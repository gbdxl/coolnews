package com.xianrou.zhihudaily.ui.main;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.xianrou.zhihudaily.R;
import com.xianrou.zhihudaily.base.BaseFragment;
import com.xianrou.zhihudaily.events.TabSelectEvent;
import com.xianrou.zhihudaily.ui.main.adapter.ZhihuMainAdapter;
import com.xianrou.zhihudaily.ui.zhihu.DailyFragment;
import com.xianrou.zhihudaily.ui.zhihu.HotFragment;
import com.xianrou.zhihudaily.ui.zhihu.SectionFragment;
import com.xianrou.zhihudaily.ui.zhihu.ThemeFragment;
import com.xianrou.zhihudaily.uitls.RxBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by android studio.
 * user 磊
 * Date 2016/9/26
 * Time 17:53
 * Desc
 */

public class ZhihuFragment extends BaseFragment {
	@BindView(R.id.toolbar)
	Toolbar toolbar;
	@BindView(R.id.view_pager)
	ViewPager mViewPager;
	@BindView(R.id.fab)
	FloatingActionButton fab;
	@BindView(R.id.tab_layout)
	TabLayout mTabLayout;

	String[] tabTitle = new String[]{"日报", "主题", "专栏", "热门"};
	List<Fragment> fragments = new ArrayList<Fragment>();

	ZhihuMainAdapter mAdapter;

	@Override
	protected int getContentViewLayoutID() {
		return R.layout.fragment_zhihu;
	}

	@Override
	protected void initData() {

	}

	@Override
	protected void initViews(View view) {
		((MainActivity) getActivity()).setDrawerToggle(toolbar);
		toolbar.setTitle("知乎日报");
		fragments.add(new DailyFragment());
		fragments.add(new ThemeFragment());
		fragments.add(new SectionFragment());
		fragments.add(new HotFragment());
		mAdapter = new ZhihuMainAdapter(getChildFragmentManager(), fragments, tabTitle);
		mViewPager.setAdapter(mAdapter);
		mTabLayout.setupWithViewPager(mViewPager);
		addListeners();
	}

	private void addListeners() {
		mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
			@Override
			public void onTabSelected(TabLayout.Tab tab) {
				if (tab.getPosition() == 0) {
					fab.setVisibility(View.VISIBLE);
				}else{
					fab.setVisibility(View.GONE);
				}

			}

			@Override
			public void onTabUnselected(TabLayout.Tab tab) {

			}

			@Override
			public void onTabReselected(TabLayout.Tab tab) {

			}
		});
		fab.setOnClickListener(v -> RxBus.getInstance().send(new TabSelectEvent()));
	}
}
