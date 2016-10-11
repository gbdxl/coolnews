package com.xianrou.zhihudaily.ui.zhihu;

import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.xianrou.zhihudaily.R;
import com.xianrou.zhihudaily.base.BaseFragment;
import com.xianrou.zhihudaily.bean.OthersBean;
import com.xianrou.zhihudaily.bean.ThemeListBean;
import com.xianrou.zhihudaily.presenter.ThemePresenter;
import com.xianrou.zhihudaily.presenter.contractor.ThemeContractor;
import com.xianrou.zhihudaily.ui.zhihu.adapter.ThemeAdapter;
import com.xianrou.zhihudaily.widget.GridSpacingItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by android studio.
 * user 磊
 * Date 2016/9/26
 * Time 18:12
 * Desc
 */
public class ThemeFragment extends BaseFragment<ThemePresenter>
		implements ThemeContractor.View,SwipeRefreshLayout.OnRefreshListener {

	@BindView(R.id.recycler_view)
	RecyclerView mRecyclerView;
	@BindView(R.id.swipe_layout)
	SwipeRefreshLayout mSwipeLayout;

	private List<OthersBean> mList = new ArrayList<>();
	private ThemeAdapter mAdapter;

	@Override
	protected int getContentViewLayoutID() {
		return R.layout.common_fagment;
	}

	@Override
	protected void initData() {
		mPresenter = new ThemePresenter();
		mPresenter.getData();
	}

	@Override
	protected void initViews(View view) {
		mSwipeLayout.setOnRefreshListener(this);
		mRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
		mAdapter = new ThemeAdapter(R.layout.item_theme, mList);
		mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(2, 6, true));
		mRecyclerView.setAdapter(mAdapter);
		mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
			@Override
			public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
				if (mList.size() > 0){
					ActivityOptionsCompat options
							= ActivityOptionsCompat.makeSceneTransitionAnimation(mActivity, view, getString(R.string.theme_share));
					ThemeActivity.launch(mActivity, mList.get(i).id, options.toBundle());
					mActivity.overridePendingTransition(0, 0);
				}

			}
		});
	}

	@Override
	public void showContent(ThemeListBean bean) {
		mList.clear();
		mList.addAll(bean.others);
		mAdapter.dataAdded();
	}

	@Override
	public void hideRefresh() {
		mSwipeLayout.setRefreshing(false);
	}

	@Override
	public void onRefresh() {
		mSwipeLayout.setRefreshing(true);
		mPresenter.getData();
	}
}
