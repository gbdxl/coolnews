package com.xianrou.zhihudaily.ui.zhihu.adapter;

import android.support.v4.content.ContextCompat;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xianrou.zhihudaily.R;
import com.xianrou.zhihudaily.bean.RecentBean;

import java.util.List;

/**
 * Created by 磊.
 * Date 2016/10/12 17:29
 */

public class HotAdapter extends BaseQuickAdapter<RecentBean> {
	public HotAdapter(int layoutResId, List<RecentBean> data) {
		super(layoutResId, data);
	}

	@Override
	protected void convert(BaseViewHolder baseViewHolder, RecentBean recentBean) {
		baseViewHolder.setText(R.id.tv_daily_item_title, recentBean.title)
				.setTextColor(R.id.tv_daily_item_title, recentBean.readState ?
						ContextCompat.getColor(mContext, R.color.news_read) :
						ContextCompat.getColor(mContext, R.color.news_unread));
		Glide.with(mContext)
				.load(recentBean.thumbnail)
				.centerCrop()
				.into((ImageView) baseViewHolder.getView(R.id.iv_daily_item_image));
	}
}
