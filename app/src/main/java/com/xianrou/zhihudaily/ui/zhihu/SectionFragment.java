package com.xianrou.zhihudaily.ui.zhihu;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.xianrou.zhihudaily.R;
import com.xianrou.zhihudaily.base.BaseFragment;
import com.xianrou.zhihudaily.bean.SectionBean;
import com.xianrou.zhihudaily.bean.SectionListBean;
import com.xianrou.zhihudaily.presenter.SectionPresenter;
import com.xianrou.zhihudaily.presenter.contractor.SectionContractor;
import com.xianrou.zhihudaily.ui.zhihu.adapter.SectionAdapter;
import com.xianrou.zhihudaily.widget.GridSpacingItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by android studio.
 * user 磊
 * Date 2016/9/26
 * Time 18:13
 * Desc
 */
public class SectionFragment extends BaseFragment<SectionPresenter>
		implements SectionContractor.View, SwipeRefreshLayout.OnRefreshListener {

	@BindView(R.id.recycler_view)
	RecyclerView recyclerView;
	@BindView(R.id.swipe_layout)
	SwipeRefreshLayout swipeLayout;

	private List<SectionBean> mList = new ArrayList<>();
	private SectionAdapter mAdapter;

	@Override
	protected int getContentViewLayoutID() {
		return R.layout.common_fagment;
	}

	@Override
	protected void initData() {
		mPresenter.getData();
	}

	@Override
	protected void initViews(View view) {
		mPresenter = new SectionPresenter();
		swipeLayout.setOnRefreshListener(this);
		recyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
		recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, 6, true));
		mAdapter = new SectionAdapter(R.layout.item_theme, mList);
		recyclerView.setAdapter(mAdapter);
		recyclerView.addOnItemTouchListener(new OnItemClickListener() {
			@Override
			public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {

			}
		});
		mAdapter.setEmptyView(View.inflate(mContext,R.layout.empty_view,null));
	}

	@Override
	public void showContent(SectionListBean beanList) {
		mAdapter.addData(beanList.data);
	}

	@Override
	public void onRefresh() {
		swipeLayout.setRefreshing(true);
		mPresenter.getData();
	}

	@Override
	public void hideLoadingView() {
		swipeLayout.setRefreshing(false);
	}
}
