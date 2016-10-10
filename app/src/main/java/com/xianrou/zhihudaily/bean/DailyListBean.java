package com.xianrou.zhihudaily.bean;

import java.util.List;

/**
 * Created by codeest on 16/8/12.
 */

public class DailyListBean{

	public static final int TOP_STORIES = 1;
	public static final int STORIES = 2;
	private int itemType;

	public String date;

	public List<StoriesBean> stories;

	public List<TopStoriesBean> top_stories;



}
