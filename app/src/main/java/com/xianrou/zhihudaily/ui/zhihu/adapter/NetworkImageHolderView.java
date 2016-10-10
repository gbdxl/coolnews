package com.xianrou.zhihudaily.ui.zhihu.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.xianrou.zhihudaily.bean.TopStoriesBean;

public class NetworkImageHolderView implements Holder<TopStoriesBean> {
	private ImageView imageView;

	@Override
	public View createView(Context context) {
		//你可以通过layout文件来创建，也可以像我一样用代码创建，不一定是Image，任何控件都可以进行翻页
		imageView = new ImageView(context);
		imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
		return imageView;
	}

	@Override
	public void UpdateUI(Context context, int position, TopStoriesBean data) {
		if (!TextUtils.isEmpty(data.image)) {
			Glide.with(context).load(data.image)
					.into(imageView);
		}
	}
}