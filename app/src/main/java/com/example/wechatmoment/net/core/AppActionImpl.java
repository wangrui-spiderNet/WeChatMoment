package com.example.wechatmoment.net.core;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.example.wechatmoment.Constants;
import com.example.wechatmoment.net.PostRequestCallBack;
import com.example.wechatmoment.net.api.JNApi;
import com.example.wechatmoment.net.api.JNApiImpl;
import com.example.wechatmoment.util.StringUtil;

/**
 * Created by wangrui on 2016/11/8.
 */
public class AppActionImpl implements AppAction {

    private Context context;
    private JNApi api;
    private int errorCode = 123;

    public static AppAction getInstance( Context context){

        AppAction appAction=null;

        if(appAction==null){
            appAction = new AppActionImpl(context);
        }

        return appAction;
    }

    public AppActionImpl(Context context) {
        this.context = context;
        this.api = new JNApiImpl();
    }

    private int getReslutCode(String json) {//判断接口返回时候，错误码
        int stat = 0;

        if(!StringUtil.isEmpty(json)){
            stat=Constants.ErrorCode.ERROR_API_OK;
        }

        return stat;
    }

    private void getPostResult(String result, PostRequestCallBack mJnPostRequestCallBack) {
        Log.e(Constants.APP_TAG, "接口返回" + result);
        if (result != null) {
            result = StringUtil.formatString(result);
            if (mJnPostRequestCallBack != null) {
                int code = getReslutCode(result);
                if (code == Constants.ErrorCode.ERROR_API_OK) {
                    mJnPostRequestCallBack.success(result);
                } else {
                    if (code == Constants.ErrorCode.ERROR_API_NO_HAVE_USER_INFO) {
                        Intent intent = new Intent(Constants.BROADCASTKEY.EXIT_USER);
                        context.sendBroadcast(intent);
                    } else if (code == Constants.ErrorCode.ERROR_API_USER_STOP_USE) {
                        context.sendBroadcast(new Intent(Constants.BROADCASTKEY.EXIT_USER));
                    } else {
                        mJnPostRequestCallBack.serviceFaild(code, result);
                    }
                }
            }
        } else {
            if (mJnPostRequestCallBack != null) {
                mJnPostRequestCallBack.localFaild(errorCode);
            }
        }
        if (mJnPostRequestCallBack != null) {
            mJnPostRequestCallBack.endPost();
        }
    }

    @Override
    public void getUser(final PostRequestCallBack requestCallBack) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                return api.getUser();
            }

            @Override
            protected void onPostExecute(String result) {
                getPostResult(result, requestCallBack);
                super.onPostExecute(result);
            }
        }.execute();
    }

    @Override
    public void getWeChatMomentList(final PostRequestCallBack requestCallBack) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                return api.getWeChatMomentList();
            }

            @Override
            protected void onPostExecute(String result) {
                getPostResult(result, requestCallBack);
                super.onPostExecute(result);
            }
        }.execute();

    }
}
