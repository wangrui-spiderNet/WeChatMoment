package com.example.wechatmoment;

import android.app.Application;

/**
 * Created by wangrui on 2016/11/8.
 */

public class MyApplication extends Application {

    private static MyApplication instanse;

    @Override
    public void onCreate() {
        super.onCreate();
        instanse=this;
    }

    public static MyApplication getInstance() {
        return instanse;
    }

}
