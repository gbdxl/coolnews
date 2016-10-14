package com.xianrou.zhihudaily.ui.function.adapter;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xianrou.zhihudaily.R;
import com.xianrou.zhihudaily.bean.ReadBean;
import com.xianrou.zhihudaily.widget.guideview.Guide;
import com.xianrou.zhihudaily.widget.guideview.GuideBuilder;
import com.xianrou.zhihudaily.widget.guideview.MutiComponent;

import java.util.List;

/**
 * Created by 磊.
 * Date 2016/10/14 11:32
 */

public class FavoriteAdapter extends BaseItemDraggableAdapter<ReadBean> {

	private int showTimes;

	public FavoriteAdapter(int layoutResId, List<ReadBean> data) {
		super(layoutResId, data);
	}

	@Override
	protected void convert(BaseViewHolder baseViewHolder, ReadBean bean) {
		baseViewHolder.setText(R.id.tv_daily_item_title, bean.title);
		Glide.with(mContext)
				.load(bean.cover)
				.centerCrop()
				.into((ImageView) baseViewHolder.getView(R.id.iv_daily_item_image));
		if (baseViewHolder.getLayoutPosition() == 1 && showTimes == 0) {
//			baseViewHolder.convertView.post(() ->
//					showGuideView(baseViewHolder.convertView)
//			);
		}
	}

	public void showGuideView(View targetView) {
		showTimes++;
		GuideBuilder builder = new GuideBuilder();
		builder.setTargetView(targetView)
				.setFullingViewId(R.id.container)
				.setAlpha(150)
				.setHighTargetCorner(20)
				.setHighTargetPadding(10)
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
		guide.show((Activity) mContext);
	}
}
