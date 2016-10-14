package com.xianrou.zhihudaily.ui.function;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.ViewTreeObserver;

import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;
import com.xianrou.zhihudaily.R;
import com.xianrou.zhihudaily.base.BaseFragment;
import com.xianrou.zhihudaily.bean.ReadBean;
import com.xianrou.zhihudaily.presenter.FavoritePresenter;
import com.xianrou.zhihudaily.presenter.contractor.FavoriteContractor;
import com.xianrou.zhihudaily.ui.function.adapter.FavoriteAdapter;
import com.xianrou.zhihudaily.widget.guideview.Guide;
import com.xianrou.zhihudaily.widget.guideview.GuideBuilder;
import com.xianrou.zhihudaily.widget.guideview.MutiComponent;

import java.util.ArrayList;
import java.util.List;

import app.dinus.com.loadingdrawable.LoadingView;
import butterknife.BindView;

/**
 * Created by 磊.
 * Date 2016/10/14 10:43
 */

public class FavoriteFragment extends BaseFragment<FavoritePresenter>
		implements FavoriteContractor.View {

	@BindView(R.id.toolbar)
	Toolbar toolbar;
	@BindView(R.id.recycler_view)
	RecyclerView recyclerView;
	@BindView(R.id.level_view)
	LoadingView levelView;
	@BindView(R.id.guide_target)
	View guideTarget;
	private FavoriteAdapter mAdapter;
	private List<ReadBean> mList = new ArrayList<>();
	private long showTimes;

	@Override
	protected int getContentViewLayoutID() {
		return R.layout.fragment_favorite;
	}

	@Override
	protected void initData() {
		mPresenter.getData();
	}

	@Override
	protected void initViews(View view) {
		mPresenter = new FavoritePresenter();
		recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
		mAdapter = new FavoriteAdapter(R.layout.item_hot, mList);
		recyclerView.setAdapter(mAdapter);
		ItemDragAndSwipeCallback itemDragAndSwipeCallback = new ItemDragAndSwipeCallback(mAdapter);
		ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemDragAndSwipeCallback);
		itemTouchHelper.attachToRecyclerView(recyclerView);
		mAdapter.enableSwipeItem();
	}

	@Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		getActivity().getWindow()
				.getDecorView()
				.getViewTreeObserver()
				.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
					@Override public void onGlobalLayout() {
						if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
							getActivity().getWindow()
									.getDecorView()
									.getViewTreeObserver()
									.removeOnGlobalLayoutListener(this);
						} else {
							getActivity().getWindow()
									.getDecorView()
									.getViewTreeObserver()
									.removeGlobalOnLayoutListener(this);
						}
						showGuideView(guideTarget);
					}
				});
	}

	public void showGuideView(View targetView) {
		showTimes++;
		GuideBuilder builder = new GuideBuilder();
		builder.setTargetView(targetView)
				.setAlpha(150)
				.setHighTargetCorner(20)
				.setHighTargetPaddingBottom(130)
				.setOverlayTarget(false)
				.setOutsideTouchable(false);
		builder.setOnVisibilityChangedListener(new GuideBuilder.OnVisibilityChangedListener() {
			@Override public void onShown() {
			}

			@Override public void onDismiss() {
			}
		});

		builder.addComponent(new MutiComponent());
		Guide guide = builder.createGuide();
		guide.setShouldCheckLocInWindow(true);
		guide.show(mActivity);
	}

	@Override
	public void showContent(List<ReadBean> list) {
		mList.addAll(list);
		mAdapter.dataAdded();
	}

	@Override
	public void showLoadingView() {
		levelView.setVisibility(View.VISIBLE);
		recyclerView.setVisibility(View.GONE);
	}
}
