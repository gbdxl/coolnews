package com.xianrou.zhihudaily.http;

/**
 * Created by 磊.
 * Date 2016/10/13 12:44
 */

public class RealmHelper {

//	private final String DB_NAME = "zhihu.realm";
//	private final Realm mRealm;
//
//	public RealmHelper() {
//		mRealm = Realm.getInstance(new RealmConfiguration.Builder()
//				.name(DB_NAME)
//				.deleteRealmIfMigrationNeeded()
//				.build());
//	}
//
//	public void insertReadNews(int id) {
//		mRealm.executeTransaction(realm -> {
//			realm.copyToRealm(new ReadBean(id));
//		});
//	}
//
//	public boolean queryReadNews(int id) {
//		RealmResults<ReadBean> all = mRealm.where(ReadBean.class)
//				.contains(ReadBean.FIELD_ID_NAME, String.valueOf(id))
//				.findAll();
//		return all.size() == 1;
//	}
}
