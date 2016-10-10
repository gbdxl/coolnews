package com.xianrou.zhihudaily.ui.zhihu.adapter;

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
 * Date 2016/9/27
 * Time 18:22
 * Desc
 */

public class DailyAdapter extends BaseQuickAdapter<StoriesBean> {

	public DailyAdapter(int layoutResId, List<StoriesBean> data) {
		super(layoutResId, data);
	}

	@Override
	protected void convert(BaseViewHolder holder, StoriesBean storiesBean) {
		holder.setText(R.id.tv_daily_date, "今日新闻")
				.setText(R.id.tv_daily_item_title,storiesBean.title);
		int position = holder.getAdapterPosition();
		if (position == 1) {
			holder.setVisible(R.id.tv_daily_date, true);
		} else {
			holder.setVisible(R.id.tv_daily_date, false);
		}
		Glide.with(mContext).load(storiesBean.images.get(0)).into((ImageView) holder.getView(R.id.iv_daily_item_image));
	}
}
