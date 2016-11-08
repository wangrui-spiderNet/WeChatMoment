package test;

import android.test.AndroidTestCase;
import com.example.wechatmoment.entity.UserInfo;

/**
 * Created by wangrui on 2016/11/8.
 */
public class UserInfoServiceTest extends AndroidTestCase {

    public void testSave() throws Exception{
        UserInfoService service=new UserInfoService();
        UserInfo userInfo=new UserInfo();
        service.saveUser(getContext(),userInfo);
    }

    public void testGetFromD() throws Exception{
        UserInfoService service=new UserInfoService();
        UserInfo userInfo= service.getUserInfo(getContext());
        assertEquals(userInfo!=null,true);
    }

}
