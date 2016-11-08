package test;


import android.content.Context;

import com.example.wechatmoment.db.DatabaseDao;
import com.example.wechatmoment.entity.UserInfo;

/**
 * Created by wangrui on 2016/11/8.
 */
public class UserInfoService {

    public void saveUser(Context context,UserInfo userInfo){

        DatabaseDao.getInstance(context).saveUserInfo(userInfo);

    }

    public UserInfo getUserInfo(Context context){

        UserInfo userInfo=DatabaseDao.getInstance(context).getUser();

        return userInfo;

    }

}
