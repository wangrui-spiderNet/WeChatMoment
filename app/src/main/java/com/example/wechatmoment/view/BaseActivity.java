package com.example.wechatmoment.view;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.example.wechatmoment.Constants;
import com.example.wechatmoment.R;
import com.example.wechatmoment.net.core.AppAction;
import com.example.wechatmoment.net.core.AppActionImpl;
import com.example.wechatmoment.util.StringUtil;

/**
 * Created by wangrui on 2016/11/8.
 */
public class BaseActivity extends FragmentActivity implements View.OnClickListener{

    public AppAction appAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (StringUtil.isEmpty(Constants.SERVER_HOST_ADDRESS)) {
            Constants.SERVER_HOST_ADDRESS = getResources().getString(R.string.server_host);
        }

        appAction = AppActionImpl.getInstance(this);
    }

    @Override
    public void onClick(View v) {

    }
}
