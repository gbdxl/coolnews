package com.xianrou.zhihudaily.ui.zhihu.adapter;

import android.support.v4.content.ContextCompat;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xianrou.zhihudaily.R;
import com.xianrou.zhihudaily.bean.StoriesBean;

import java.util.List;

/**
 * Created by android studio.
 * user 磊
 * Date 2016/10/10
 * Time 19:05
 * Desc
 */

public class ThemeActivityAdapter extends BaseQuickAdapter<StoriesBean> {

	public ThemeActivityAdapter(int layoutResId, List<StoriesBean> data) {
		super(layoutResId, data);
	}

	@Override
	protected void convert(BaseViewHolder holder, StoriesBean storiesBean) {
		holder.setText(R.id.tv_daily_item_title,storiesBean.title)
				.setVisible(R.id.tv_daily_date, false)
				.setTextColor(R.id.tv_daily_item_title,storiesBean.readState?
						ContextCompat.getColor(mContext, R.color.news_read) :
						ContextCompat.getColor(mContext, R.color.news_unread));
		if (null != storiesBean.images) {
			Glide.with(mContext).load(storiesBean.images.get(0)).
					into((ImageView) holder.getView(R.id.iv_daily_item_image));
		}
	}
}
