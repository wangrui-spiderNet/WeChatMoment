package test;

import android.test.AndroidTestCase;

import com.example.wechatmoment.entity.DbMoment;

import java.util.List;

/**
 * Created by 94540 on 2015/11/24.
 */
public class WeChatMomentServiceTest extends AndroidTestCase{
    public void textSave() throws Exception{
        WeChatMomentService momentService=new WeChatMomentService();
        DbMoment moment=new DbMoment();
        moment.setOrder(100);
        momentService.saveWechatMoment(getContext(),moment);
    }

    public void getDbMoment() throws Exception{
        WeChatMomentService momentService=new WeChatMomentService();

        List <DbMoment> dbMoments = momentService.getWeChatMomentByOrder(getContext(), 0, 5);

        assertEquals(dbMoments.size()!=0,true);
    }
}
