package com.xianrou.zhihudaily.ui.zhihu;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xianrou.zhihudaily.R;
import com.xianrou.zhihudaily.base.BaseFragment;
import com.xianrou.zhihudaily.bean.DailyListBean;
import com.xianrou.zhihudaily.bean.StoriesBean;
import com.xianrou.zhihudaily.events.TabSelectEvent;
import com.xianrou.zhihudaily.presenter.DailyPresenter;
import com.xianrou.zhihudaily.presenter.contractor.DailyContractor;
import com.xianrou.zhihudaily.ui.zhihu.adapter.DailyAdapter;
import com.xianrou.zhihudaily.ui.zhihu.adapter.NetworkImageHolderView;
import com.xianrou.zhihudaily.uitls.RxBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import rx.Subscription;

/**
 * Created by android studio.
 * user 磊
 * Date 2016/9/26
 * Time 18:12
 * Desc
 */
public class DailyFragment extends BaseFragment<DailyContractor.Presenter>
				implements DailyContractor.View ,SwipeRefreshLayout.OnRefreshListener{

	@BindView(R.id.recycler_view)
	RecyclerView mRecyclerView;
	@BindView(R.id.swipe_layout)
	SwipeRefreshLayout mSwipeLayout;

	private List<StoriesBean> storiesBeen ;
	private DailyAdapter mDailyAdapter;
	private DailyListBean mListBean;
	private ConvenientBanner mBanner;
	private View mHeader;
	private String lastDate;

	@Override
	public void onRefresh() {
		mPresenter.getData();
		mSwipeLayout.setRefreshing(true);
	}

	@Override
	protected int getContentViewLayoutID() {
		return R.layout.common_fagment;
	}

	@Override
	protected void initData() {
		mPresenter = new DailyPresenter();
		mPresenter.getData();

	}

	@Override
	protected void initViews(View view) {
		mSwipeLayout.setOnRefreshListener(this);
		storiesBeen = new ArrayList<>();
		LinearLayoutManager manager = new LinearLayoutManager(mContext);
		mRecyclerView.setLayoutManager(manager);
		mDailyAdapter = new DailyAdapter(R.layout.item_dialy, storiesBeen);
		mDailyAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
		mRecyclerView.setAdapter(mDailyAdapter);
		addListeners();
		registerEvent();
	}

	private void registerEvent() {
		Subscription subscribe = RxBus.getInstance()
				.toObserverable()
				.subscribe(o -> {
					if (o instanceof TabSelectEvent)
						mRecyclerView.scrollToPosition(0);
				});
		addSubscribe(subscribe);
	}

	private void addListeners() {
		mDailyAdapter.setOnLoadMoreListener(() -> {
			mRecyclerView.post(() -> {
				if(lastDate != null)
					mPresenter.getMoreData(lastDate);
			});
		});
	}

	private View getHeaderView() {
		mHeader = View.inflate(mContext, R.layout.banner, null);
		mBanner = (ConvenientBanner) mHeader.findViewById(R.id.home_viewpager);
		mBanner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
			@Override
			public NetworkImageHolderView createHolder() {
				return new NetworkImageHolderView();
			}
		}, mListBean.top_stories)
				//设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
				.setPageIndicator(new int[]{R.drawable.indicator_normal, R.drawable.indicator_selected})
				//设置指示器的方向
				.setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
		mBanner.startTurning(5000);
		return mHeader;
	}

	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
		if (mBanner != null) {
			if(isVisibleToUser && !mBanner.isTurning())
				mBanner.startTurning(5000);
			else if (!isVisibleToUser && mBanner.isTurning())
				mBanner.stopTurning();
		}
	}

	@Override
	public void showContent(DailyListBean listBean) {
		mListBean = listBean;
		storiesBeen.clear();
		storiesBeen.addAll(listBean.stories);
		mDailyAdapter.dataAdded();
		lastDate = listBean.date;
		mDailyAdapter.openLoadMore(listBean.stories.size());
		if (mHeader != null)
			mDailyAdapter.removeHeaderView(mHeader);
		mDailyAdapter.addHeaderView(getHeaderView());
	}

	@Override
	public void showMoreContent(DailyListBean listBean) {
		lastDate = listBean.date;
		mDailyAdapter.addData(listBean.stories);
	}

	@Override
	public void loadComplete() {
		mDailyAdapter.loadComplete();
	}

	@Override
	public void hideRefresh() {
		mSwipeLayout.setRefreshing(false);
	}

}
