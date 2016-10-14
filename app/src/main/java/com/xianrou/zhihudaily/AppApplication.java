package com.xianrou.zhihudaily;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.LeakCanary;

import io.realm.Realm;


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
		Realm.init(this);
		if (LeakCanary.isInAnalyzerProcess(this)) {
			return;
		}
		LeakCanary.install(this);
	}
	public static AppApplication getInstance() {
		return sInstance;
	}
}
