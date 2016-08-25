package com.example.wechatmoment;

import android.app.Application;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class MyApplication extends Application {

	private static MyApplication instanse;

	@Override
	public void onCreate() {
		super.onCreate();

		instanse=this;

		DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
				.showImageForEmptyUri(R.drawable.agent_default)
				.showImageOnFail(R.drawable.bid_tip_bg).cacheInMemory(true).cacheOnDisc(true)
				.build();

		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				getApplicationContext()).defaultDisplayImageOptions(defaultOptions)
				.discCacheSize(50 * 1024 * 1024)
				.discCacheFileCount(100)
				.writeDebugLogs().build();
		ImageLoader.getInstance().init(config);
	}


	public static MyApplication getInstance() {
		return instanse;
	}

}
