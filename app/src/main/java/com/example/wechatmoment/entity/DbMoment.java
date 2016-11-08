package com.example.wechatmoment.entity;

/**
 * Created by wangrui on 2016/11/8.
 */
public class DbMoment {
    private int moment_id;
    private int order;
    private String content;

    public int getMoment_id() {
        return moment_id;
    }

    public void setMoment_id(int moment_id) {
        this.moment_id = moment_id;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
