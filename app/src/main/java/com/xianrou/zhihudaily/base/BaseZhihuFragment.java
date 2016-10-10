package com.xianrou.zhihudaily.base;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.xianrou.zhihudaily.R;

import butterknife.BindView;

/**
 * Created by android studio.
 * user 磊
 * Date 2016/9/27
 * Time 10:54
 * Desc
 */

public abstract class BaseZhihuFragment<T> extends BaseFragment
		implements SwipeRefreshLayout.OnRefreshListener{

	@BindView(R.id.recycler_view)
	protected RecyclerView mRecyclerView;
	@BindView(R.id.swipe_layout)
	protected SwipeRefreshLayout mSwipeLayout;

	@Override
	protected int getContentViewLayoutID() {
		return R.layout.common_fagment;
	}

	@Override
	protected void initViews(View view) {
		mSwipeLayout.setOnRefreshListener(this);
	}

	@Override
	public void onRefresh() {
		onRefreshData();
	}

	protected abstract void onRefreshData();
}
