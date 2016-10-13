package com.xianrou.zhihudaily.bean;

import io.realm.RealmObject;

/**
 * Created by 磊.
 * Date 2016/10/13 12:32
 */

public class ReadBean extends RealmObject {

	public static final String FIELD_ID_NAME = "id";
	public static final int TYPE_ZHIHU = 0;
	public static final int TYPE_WEIXIN = 1;
	public static final int TYPE_WANGYI = 2;

	public int type;
	public String id;

	public String cover;
	public String title;

	public ReadBean() {
		super();
	}

	public ReadBean(int type, String id, String cover, String title) {
		this.type = type;
		this.id = id;
		this.cover = cover;
		this.title = title;
	}
}
