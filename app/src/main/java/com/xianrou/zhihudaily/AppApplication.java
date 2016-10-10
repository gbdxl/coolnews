package com.xianrou.zhihudaily;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.orhanobut.logger.Logger;


/**
 * Created by android studio.
 * user 磊
 * Date 2016/9/26
 * Time 16:44
 * Desc
 */

public class AppApplication extends Application {

	public static AppApplication sInstance;

	@Override
	public void onCreate() {
		super.onCreate();
		sInstance = this;
		init();

	}

	private void init() {
		Logger.init(getPackageName()).hideThreadInfo();
		Stetho.initialize(
				Stetho.newInitializerBuilder(this)
						.enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
						.enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
						.build());
	}
	public static AppApplication getInstance() {
		return sInstance;
	}
}
