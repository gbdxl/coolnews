package com.xianrou.zhihudaily.ui.zhihu.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xianrou.zhihudaily.R;
import com.xianrou.zhihudaily.bean.SectionBean;

import java.util.List;

/**
 * Created by 磊.
 * Date 2016/10/12 16:21
 */

public class SectionAdapter extends BaseQuickAdapter<SectionBean> {

	public SectionAdapter(int layoutResId, List<SectionBean> data) {
		super(layoutResId, data);
	}

	@Override
	protected void convert(BaseViewHolder holder, SectionBean sectionBean) {
		holder.setText(R.id.title, sectionBean.name)
				.setText(R.id.description, sectionBean.description);
		Glide.with(mContext)
				.load(sectionBean.thumbnail)
				.centerCrop()
				.into((ImageView) holder.getView(R.id.cover));
	}
}
