package com.example.wechatmoment.net.api;

import com.example.wechatmoment.Constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangrui on 2016/11/8.
 */
public class JNApiImpl implements JNApi {

    private HttpEngine httpEngine;

    public JNApiImpl() {
        httpEngine = HttpEngine.getInstance();
    }

    @Override
    public String getUser() {
        try {
            Map<String, String> values = new HashMap<String, String>();
            return httpEngine.postHandle(values,Constants.SERVER_HOST_ADDRESS+ Constants.API_USER_INFO);
        } catch (Exception e) {
            return  e.getMessage();
        }
    }


    @Override
    public String getWeChatMomentList() {
        try {
            Map<String, String> values = new HashMap<String, String>();
            return httpEngine.postHandle(values,Constants.SERVER_HOST_ADDRESS+ Constants.API_USER_TWEETS);
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
