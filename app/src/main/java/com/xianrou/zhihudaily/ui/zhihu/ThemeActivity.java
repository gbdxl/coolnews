package com.xianrou.zhihudaily.ui.zhihu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xianrou.zhihudaily.R;
import com.xianrou.zhihudaily.base.BaseActivity;
import com.xianrou.zhihudaily.bean.StoriesBean;
import com.xianrou.zhihudaily.bean.ThemeChildListBean;
import com.xianrou.zhihudaily.presenter.ThemeActivityPresenter;
import com.xianrou.zhihudaily.presenter.contractor.ThemeActivityContractor;
import com.xianrou.zhihudaily.ui.zhihu.adapter.ThemeActivityAdapter;

import net.opacapp.multilinecollapsingtoolbar.CollapsingToolbarLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by android studio.
 * user 磊
 * Date 2016/10/10
 * Time 18:10
 * Desc
 */

public class ThemeActivity extends BaseActivity<ThemeActivityPresenter>
		implements ThemeActivityContractor.View {

	private static final String EXTRA_ID = "extra_id";
	@BindView(R.id.detail_bar_image)
	ImageView detailBarImage;
	@BindView(R.id.detail_bar_copyright)
	TextView detailBarCopyright;
	@BindView(R.id.toolbar)
	Toolbar viewToolbar;
	@BindView(R.id.clp_toolbar)
	CollapsingToolbarLayout clpToolbar;
	@BindView(R.id.app_bar)
	AppBarLayout appBar;
	@BindView(R.id.recycler_view)
	RecyclerView mRecyclerView;

	private List<StoriesBean> mList = new ArrayList<>();
	private ThemeActivityAdapter mAdapter;
	private ThemeChildListBean mBean;

	public static void launch(Activity activity, int id, Bundle bundle) {
		Intent intent = new Intent(activity, ThemeActivity.class);
		intent.putExtra(EXTRA_ID, id);
		activity.startActivity(intent, bundle);
	}

	@Override
	public void finish() {
		super.finish();
		overridePendingTransition(0, 0);
	}

	@Override
	protected int getContentId() {
		return R.layout.activity_theme;
	}

	@Override
	protected void initViews() {
		viewToolbar.setNavigationOnClickListener(v -> finish());
		mAdapter = new ThemeActivityAdapter(R.layout.item_dialy, mList);
		mRecyclerView.setAdapter(mAdapter);
		appBar.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
			if (mBean != null) {
				if (verticalOffset != 0) {
					clpToolbar.setTitle(mBean.name);
				} else {
					clpToolbar.setTitle(mBean.description);
				}
			}
		});
	}

	@Override
	protected void initData() {
		int intExtra = getIntent().getIntExtra(EXTRA_ID, 0);
		if (intExtra == 0)
			finish();
		mPresenter = new ThemeActivityPresenter();
		mPresenter.getData(intExtra);
	}

	@Override
	public void showContent(ThemeChildListBean bean) {
		mBean = bean;
		mList.clear();
		mList.addAll(bean.stories);
		mAdapter.dataAdded();
		Glide.with(this)
				.load(bean.image)
				.centerCrop()
				.into(detailBarImage);
		viewToolbar.setTitle(bean.name);
		clpToolbar.setTitle(bean.description);
	}

}
