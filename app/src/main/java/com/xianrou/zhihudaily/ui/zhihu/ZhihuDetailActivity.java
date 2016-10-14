package com.xianrou.zhihudaily.ui.zhihu;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.xianrou.zhihudaily.R;
import com.xianrou.zhihudaily.base.BaseActivity;
import com.xianrou.zhihudaily.bean.DetailExtraBean;
import com.xianrou.zhihudaily.bean.ZhihuDetailBean;
import com.xianrou.zhihudaily.presenter.contractor.ZhihuDetailContractor;
import com.xianrou.zhihudaily.presenter.contractor.ZhihuDetailPresenter;
import com.xianrou.zhihudaily.uitls.HtmlUtil;

import app.dinus.com.loadingdrawable.LoadingView;
import butterknife.BindView;

/**
 * Created by 磊.
 * Date 2016/10/11 17:07
 */

public class ZhihuDetailActivity extends BaseActivity<ZhihuDetailPresenter> implements ZhihuDetailContractor.View {

	private static final String EXTRA_ID = "extra_id";
	@BindView(R.id.cover)
	ImageView cover;
	@BindView(R.id.title)
	TextView title;
	@BindView(R.id.author)
	TextView author;
	@BindView(R.id.clp_toolbar)
	CollapsingToolbarLayout clpToolbar;
	@BindView(R.id.app_bar)
	AppBarLayout appBar;
	@BindView(R.id.wv_detail_content)
	WebView wvDetailContent;
	@BindView(R.id.scroll_view)
	NestedScrollView scrollView;
	@BindView(R.id.toolbar)
	Toolbar toolbar;
	@BindView(R.id.level_view)
	LoadingView levelView;
	@BindView(R.id.rb_share)
	RadioButton rbShare;
	@BindView(R.id.rb_star)
	RadioButton rbStar;
	@BindView(R.id.rb_comment)
	RadioButton rbComment;
	@BindView(R.id.rb_praise)
	RadioButton rbPraise;

	private int mId;
	private ZhihuDetailBean mDetailBean;

	public static void launch(Activity activity, int id) {
		Intent intent = new Intent(activity, ZhihuDetailActivity.class);
		intent.putExtra(EXTRA_ID, id);
		activity.startActivity(intent);
	}

	@Override
	protected int getContentId() {
		return R.layout.activity_zhihu;
	}

	@Override
	protected void initViews() {
		mPresenter = new ZhihuDetailPresenter();
		mId = getIntent().getIntExtra(EXTRA_ID, 0);
		if (mId == 0)
			finish();
		mToolbar.setNavigationOnClickListener(v -> finish());
		WebSettings settings = wvDetailContent.getSettings();
		settings.setAppCacheEnabled(true);
		settings.setDomStorageEnabled(true);
		settings.setDatabaseEnabled(true);
		settings.setJavaScriptEnabled(true);
		settings.setLoadWithOverviewMode(true);
		settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
		settings.setSupportZoom(true);
		wvDetailContent.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView webView, String s) {
				wvDetailContent.loadUrl(s);
				return true;
			}
		});
		addListeners();
	}

	private void addListeners() {
		rbShare.setOnClickListener(v -> {
			Intent intent = new Intent(Intent.ACTION_SEND);
			intent.setType("text/plain");
			intent.putExtra(Intent.EXTRA_TEXT, mDetailBean.share_url);
			startActivity(intent.createChooser(intent, mDetailBean.title));
		});
	}

	@Override
	protected void initData() {

		mPresenter.getDetailData(mId);
		mPresenter.getExtraData(mId);
	}

	@Override
	public void showContent(ZhihuDetailBean detailBean) {
		mDetailBean = detailBean;
		Glide.with(mContext)
				.load(detailBean.image)
				.centerCrop()
				.into(cover);
		title.setText(detailBean.title);
		author.setText(detailBean.image_source);
		String htmlData = HtmlUtil.createHtmlData(detailBean.body, detailBean.css, detailBean.js);
		wvDetailContent.loadData(htmlData, HtmlUtil.MIME_TYPE, HtmlUtil.ENCODING);
	}

	@Override
	public void showExtraData(DetailExtraBean extraBean) {
		rbComment.setText(extraBean.comments + "");
		rbPraise.setText(extraBean.popularity + "");
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && wvDetailContent.canGoBack()) {
			wvDetailContent.goBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void showLoadingView() {
		levelView.setVisibility(View.VISIBLE);
		wvDetailContent.setVisibility(View.GONE);
	}

	@Override
	public void hideLoadingView() {
		levelView.setVisibility(View.GONE);
		wvDetailContent.setVisibility(View.VISIBLE);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		wvDetailContent.destroy();
	}
}
