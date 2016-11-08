
package com.example.wechatmoment.net.core;

import com.example.wechatmoment.net.PostRequestCallBack;

/**
 * Created by wangrui on 2016/11/8.
 */
public interface AppAction {
    /**
     * 获取用户信息
     * @return
     */
    public void getUser(PostRequestCallBack requestCallBack);

    /**
     * 获取朋友圈列表
     * @return
     */
    public void getWeChatMomentList(PostRequestCallBack requestCallBack);
}
