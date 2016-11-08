package com.example.wechatmoment.net;
/**
 * Created by wangrui on 2016/11/8.
 */
public interface PostRequestCallBack {
	public void startPost();
	public void endPost();
	public void success(String respons);
	public void localFaild(int errorCode);
	public void serviceFaild(int errorCode, String respons);
}
