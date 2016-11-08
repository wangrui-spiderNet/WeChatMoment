package test;

import android.content.Context;
import android.test.AndroidTestCase;

import com.example.wechatmoment.db.DatabaseDao;
import com.example.wechatmoment.entity.DbMoment;

import java.util.List;

/**
 * Created by wangrui on 2016/11/8.
 */
public class WeChatMomentService extends AndroidTestCase{

    public void saveWechatMoment(Context context,DbMoment moment){
        DatabaseDao.getInstance(context).saveWeChatMoments(moment);
    }

    public List<DbMoment> getWeChatMomentByOrder(Context context,int offset,int limit){
        return DatabaseDao.getInstance(context).getWetChatMoments(offset,limit);
    }

}
