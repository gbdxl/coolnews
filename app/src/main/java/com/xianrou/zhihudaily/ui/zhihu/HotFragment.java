package com.xianrou.zhihudaily.ui.zhihu;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.xianrou.zhihudaily.R;
import com.xianrou.zhihudaily.base.BaseFragment;
import com.xianrou.zhihudaily.bean.HotListBean;
import com.xianrou.zhihudaily.bean.RecentBean;
import com.xianrou.zhihudaily.presenter.HotPresenter;
import com.xianrou.zhihudaily.presenter.contractor.HotContractor;
import com.xianrou.zhihudaily.ui.zhihu.adapter.HotAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by android studio.
 * user 磊
 * Date 2016/9/26
 * Time 18:14
 * Desc
 */
public class HotFragment extends BaseFragment<HotPresenter>
		implements HotContractor.View, SwipeRefreshLayout.OnRefreshListener {

	@BindView(R.id.recycler_view)
	RecyclerView recyclerView;
	@BindView(R.id.swipe_layout)
	SwipeRefreshLayout swipeLayout;

	private List<RecentBean> mList = new ArrayList<>();
	private HotAdapter mAdapter;

	@Override
	protected int getContentViewLayoutID() {
		return R.layout.common_fagment;
	}

	@Override
	protected void initData() {
		mPresenter = new HotPresenter();
		mPresenter.getData();
		recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
		mAdapter = new HotAdapter(R.layout.item_hot, mList);
		recyclerView.setAdapter(mAdapter);
		recyclerView.addOnItemTouchListener(new OnItemClickListener() {
			@Override
			public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
				ZhihuDetailActivity.launch(mActivity, mList.get(i).news_id);
			}
		});
	}

	@Override
	protected void initViews(View view) {
		swipeLayout.setOnRefreshListener(this);
	}

	@Override
	public void showContent(HotListBean listBean) {
		mList.clear();
		mAdapter.addData(listBean.recent);
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
