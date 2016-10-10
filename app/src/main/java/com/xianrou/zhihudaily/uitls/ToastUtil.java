package com.xianrou.zhihudaily.uitls;

import android.widget.Toast;

import com.xianrou.zhihudaily.AppApplication;


/**
 * Created by lei on 2016/9/9.
 */
public class ToastUtil {
	private static Toast sToast;

	public static void showToast(String msg) {
		if (sToast == null && AppApplication.sInstance != null) {
			sToast = Toast.makeText(AppApplication.sInstance, msg, Toast.LENGTH_SHORT);
		}
		sToast.setText(msg);
		sToast.show();
	}

	public static void showToast(int res) {
		if (sToast == null && AppApplication.sInstance != null) {
			sToast = Toast.makeText(AppApplication.sInstance, res, Toast.LENGTH_SHORT);
		}
		sToast.setText(AppApplication.sInstance.getResources().getString(res));
		sToast.show();
	}

}
