package com.example.wechatmoment.entity;

import java.util.ArrayList;
/**
 * Created by wangrui on 2016/11/8.
 */
public class MyMessage {

	public int code;
	public String msg;
	
	public ArrayList<MyBean> list;

	@Override
	public String toString() {
		return "MyMessage [code=" + code + ", msg=" + msg + ", list=" + list + "]";
	}

	
	
}
