package com.xianrou.zhihudaily.ui.zhihu.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xianrou.zhihudaily.R;
import com.xianrou.zhihudaily.bean.OthersBean;

import java.util.List;

/**
 * Created by android studio.
 * user 磊
 * Date 2016/10/10
 * Time 16:21
 * Desc
 */

public class ThemeAdapter extends BaseQuickAdapter<OthersBean> {

	public ThemeAdapter(int layoutResId, List<OthersBean> data) {
		super(layoutResId, data);
	}

	@Override
	protected void convert(BaseViewHolder holder, OthersBean bean) {
		holder.setText(R.id.title, bean.name);
		Glide.with(mContext)
				.load(bean.thumbnail)
				.centerCrop()
				.into((ImageView) holder.getView(R.id.cover));
	}
}
