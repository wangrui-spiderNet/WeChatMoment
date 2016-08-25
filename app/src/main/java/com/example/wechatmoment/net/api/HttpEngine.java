package com.example.wechatmoment.net.api;

import android.util.Log;

import com.example.wechatmoment.Constants;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Http引擎处理类
 *
 * @author wangrui
 * @date 15/11/22
 * @version 1.0
 */
public class HttpEngine {
    private final static String ENCODE_TYPE = "UTF-8";
    private int  errorCode;

    private static HttpEngine instance = null;

    private HttpEngine() {

    }

    public static HttpEngine getInstance() {
        if (instance == null) {
            instance = new HttpEngine();
        }
        return instance;
    }

    public String postHandle(Map<String, String> mValues,String apiUrl){

        Log.e(Constants.APP_TAG,apiUrl+"接口参数："+mValues.toString());

        HttpGet mHttpGet = new HttpGet(apiUrl);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        HttpResponse httpResponse = null;

        for (Map.Entry<String, String> entry : mValues.entrySet()) {
            params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }

        try {
//            mHttpGet.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            params.clear();
            httpResponse = new DefaultHttpClient().execute(mHttpGet);

            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                return EntityUtils.toString(httpResponse.getEntity());
            } else {
                errorCode = Constants.ErrorCode.ERROR_LOCAL_CONNECT_SERVICE_ERROR;
            }

        } catch (UnsupportedEncodingException e) {
            errorCode = Constants.ErrorCode.ERROR_LOCAL_UNSUPPORTEDENCODINGEXCEPTION;
        } catch (ParseException e) {
            errorCode = Constants.ErrorCode.ERROR_LOCAL_PARSEEXCEPTION;
        } catch (IOException e) {
            errorCode = Constants.ErrorCode.ERROR_LOCAL_IOEXCEPTION;
        }

        return null;
    }

}
