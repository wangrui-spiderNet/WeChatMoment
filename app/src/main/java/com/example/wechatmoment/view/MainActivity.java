package com.example.wechatmoment.view;
/**
 * Created by wangrui on 2016/11/8.
 */

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wechatmoment.R;
import com.example.wechatmoment.db.DatabaseDao;
import com.example.wechatmoment.entity.DbMoment;
import com.example.wechatmoment.entity.UserInfo;
import com.example.wechatmoment.entity.WechatMoment;
import com.example.wechatmoment.net.PostRequestCallBack;
import com.example.wechatmoment.view.adapter.MyListAdapter;
import com.example.wechatmoment.view.widget.XListView;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private XListView xListView;

    private View headView;
    private ImageView ivHeadBg;
    private ImageView ivHeadImg;
    private TextView tvName;

    private MyListAdapter mAdapter;
    public static int screenWidth;
    public static int screenHeight;

    private UserInfo userInfo;
    private ArrayList<WechatMoment> wechats;
    private List<DbMoment> dbMoments;

    private int page;
    private int count=5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_photo);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels;

        wechats = new ArrayList<WechatMoment>();

        xListView = (XListView) findViewById(R.id.list);

        xListView.setPullLoadEnable(true);
        xListView.setPullRefreshEnable(true);



        xListView.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                page = 0;
                getWetChatMoments();
            }

            @Override
            public void onLoadMore() {
                page++;
                getDBmoments(page, count);
            }
        });

        initHeadView();

        mAdapter = new MyListAdapter(MainActivity.this, wechats, true);
        xListView.setAdapter(mAdapter);

        userInfo = DatabaseDao.getInstance(getApplicationContext()).getUser();

        requestUserInfo();
        getDBmoments(page, count);

        if(dbMoments==null||dbMoments.size()==0){
            getWetChatMoments();
        }

    }

    private void initHeadView() {
        headView = LayoutInflater.from(this).inflate(R.layout.layout_head_view, null);
        ivHeadBg = (ImageView) headView.findViewById(R.id.iv_bg);
        ivHeadImg = (ImageView) headView.findViewById(R.id.ivHead);
        tvName = (TextView) headView.findViewById(R.id.tv_name);
        xListView.addHeaderView(headView);

    }

    @Override
    public void onClick(View v) {

    }

    private void requestUserInfo() {

        if (userInfo == null) {
            appAction.getUser(new PostRequestCallBack() {
                @Override
                public void startPost() {

                }

                @Override
                public void endPost() {

                }

                @Override
                public void success(String respons) {

                    Gson gson = new Gson();
                    userInfo = gson.fromJson(respons, UserInfo.class);

                    ImageLoader.getInstance().displayImage(userInfo.getProfile_image(), ivHeadImg);
                    ImageLoader.getInstance().displayImage(userInfo.getAvatar(), ivHeadBg);
                    tvName.setText(userInfo.getNick());

                    DatabaseDao.getInstance(getApplicationContext()).saveUserInfo(userInfo);

                }

                @Override
                public void localFaild(int errorCode) {

                }

                @Override
                public void serviceFaild(int errorCode, String respons) {

                }
            });
        } else {
            ImageLoader.getInstance().displayImage(userInfo.getProfile_image(), ivHeadImg);
            ImageLoader.getInstance().displayImage(userInfo.getAvatar(), ivHeadBg);
            tvName.setText(userInfo.getNick());
        }

    }

    private void getWetChatMoments(){
        appAction.getWeChatMomentList(new PostRequestCallBack() {
            @Override
            public void startPost() {

            }

            @Override
            public void endPost() {


                getDBmoments(page, count);
            }

            @Override
            public void success(String respons) {

                xListView.stopLoadMore();
                xListView.stopRefresh();

                try {

                    JSONArray array = new JSONArray(respons);

                    wechats.clear();

                    for (int i = 0; i < array.length(); i++) {

                        JSONObject js = (JSONObject) array.get(i);

                        DbMoment moment = new DbMoment();
                        moment.setOrder(i);
                        moment.setMoment_id(100 + i);
                        moment.setContent(js.toString());

                        DatabaseDao.getInstance(getApplicationContext()).delMoment(moment.getMoment_id());
                        DatabaseDao.getInstance(getApplicationContext()).saveWeChatMoments(moment);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void localFaild(int errorCode) {

            }

            @Override
            public void serviceFaild(int errorCode, String respons) {

            }
        });
    }

    private void getDBmoments(int page,int count){
        dbMoments = DatabaseDao.getInstance(getApplicationContext()).getWetChatMoments(page, count);

        xListView.stopLoadMore();
        xListView.stopRefresh();

        if(page==0){
            wechats.clear();
        }

        if(dbMoments!=null){
            Gson gson = new Gson();
            for(DbMoment moment: dbMoments){
                WechatMoment wechatMoment = gson.fromJson(moment.getContent(), WechatMoment.class);
                wechats.add(wechatMoment);
            }
        }

        mAdapter.notifyDataSetChanged();
    }

}
