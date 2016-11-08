package com.example.wechatmoment.view;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.View;

import com.example.wechatmoment.Constants;
import com.example.wechatmoment.R;
import com.example.wechatmoment.net.core.AppAction;
import com.example.wechatmoment.net.core.AppActionImpl;
import com.example.wechatmoment.util.StringUtil;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by wangrui on 2016/11/8.
 */
public class BaseActivity extends FragmentActivity implements View.OnClickListener{

    public AppAction appAction;
    public static int screenWidth;
    public static int screenHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (StringUtil.isEmpty(Constants.SERVER_HOST_ADDRESS)) {
            Constants.SERVER_HOST_ADDRESS = getResources().getString(R.string.server_host);
        }

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels;
        appAction = AppActionImpl.getInstance(this);

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

    @Override
    public void onClick(View v) {

    }
}
