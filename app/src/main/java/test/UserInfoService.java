package test;


import android.content.Context;

import com.example.wechatmoment.db.DatabaseDao;
import com.example.wechatmoment.entity.UserInfo;

/**
 * Created by 94540 on 2015/11/24.
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
