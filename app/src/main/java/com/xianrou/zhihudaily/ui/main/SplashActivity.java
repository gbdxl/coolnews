package com.xianrou.zhihudaily.ui.main;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xianrou.zhihudaily.R;
import com.xianrou.zhihudaily.base.BaseActivity;
import com.xianrou.zhihudaily.bean.WelcomeBean;
import com.xianrou.zhihudaily.presenter.SplashPresenter;
import com.xianrou.zhihudaily.presenter.contractor.SplashConractor;

import butterknife.BindView;

/**
 * Created by 磊.
 * Date 2016/10/12 12:07
 */

public class SplashActivity extends BaseActivity<SplashPresenter> implements SplashConractor.View {
	@BindView(R.id.image)
	ImageView image;
	@BindView(R.id.content)
	TextView content;

	@Override
	protected int getContentId() {
		return R.layout.activity_splash;
	}

	@Override
	protected void initViews() {

	}

	@Override
	protected void initData() {
		mPresenter = new SplashPresenter();
		mPresenter.getPic();
	}

	@Override
	public void showPic(WelcomeBean welcomeBean) {
		Glide.with(mContext)
				.load(welcomeBean.img)
				.centerCrop()
				.into(image);
		image.animate().scaleX(1.15f).scaleY(1.15f).setDuration(2000).setStartDelay(100).start();
		content.setText(welcomeBean.text);
	}

	@Override
	public void jumpToMain() {
		startActivity(new Intent(this, MainActivity.class));
		finish();
		overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
	}
}
