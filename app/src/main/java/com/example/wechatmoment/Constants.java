package com.example.wechatmoment;

/**
 * Created by 94540 on 2015/11/22.
 */
public class Constants {
    public static final String APP_TAG="weichat_moment";
    public static final int DATABASE_VERSION=1;

    public static final String firstfolder="demo";
    public static final String secondfolder="cache";

    public static class ErrorCode {
        public static final int ERROR_API_OK = 1;
        public static final int ERROR_API_NO_HAVE_USER_INFO = -2;
        public static final int ERROR_API_USER_STOP_USE = -5;

        public static final int ERROR_LOCAL_CONNECT_SERVICE_ERROR = -502;
        public static final int ERROR_LOCAL_UNSUPPORTEDENCODINGEXCEPTION = -503;
        public static final int ERROR_LOCAL_PARSEEXCEPTION = -504;
        public static final int ERROR_LOCAL_IOEXCEPTION = -505;

    }

    public interface BROADCASTKEY {
          String EXIT_USER="com.example.showphotodemo.exitout";
    }

    /**
     * 接口方法
     */
    public static String SERVER_HOST_ADDRESS = "";

    public static final String API_USER_INFO="/user/jsmith";
    public static final String API_USER_TWEETS="/user/jsmith/tweets";
}
