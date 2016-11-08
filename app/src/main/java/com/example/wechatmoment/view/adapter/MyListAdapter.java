package com.example.wechatmoment.view.adapter;

import android.content.Context;
import android.text.TextUtils.TruncateAt;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wechatmoment.R;
import com.example.wechatmoment.entity.WechatMoment;
import com.example.wechatmoment.util.StringUtil;
import com.example.wechatmoment.util.SystemUtil;
import com.example.wechatmoment.view.MainActivity;
import com.example.wechatmoment.view.widget.MyGridView;
import com.example.wechatmoment.view.widget.MyListView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangrui on 2016/11/8.
 */
public class MyListAdapter extends BaseAdapter {

    private List<WechatMoment> wechats;
    private LayoutInflater mInflater;
    private Context mContext;
    private boolean isClickable = true;// 是否可以点击展开大图
    private int availableTextWidth;

    public MyListAdapter(Context context, ArrayList<WechatMoment> wechats,
                         boolean isClickable) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
        this.wechats = wechats;
        this.isClickable = isClickable;
    }

    @Override
    public int getCount() {
        return wechats == null ? 0 : wechats.size();
    }

    @Override
    public WechatMoment getItem(int position) {
        return wechats.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_list, null);
            holder = new ViewHolder(convertView);

            availableTextWidth = (MainActivity.screenWidth - SystemUtil.dip2px(mContext, 6 + 6 + 50 + 10)) * 2;
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final WechatMoment bean = getItem(position);

        if(bean!=null&&(!StringUtil.isEmpty(bean.getContent())||
                (bean.getImages()!=null&&bean.getImages().size()>0))){
            if (!StringUtil.isEmpty(bean.getContent())) {
                showContent(holder, bean.getContent(), position);
            }

            if (bean.getSender() != null && !StringUtil.isEmpty(bean.getSender().getAvatar())) {
                ImageLoader.getInstance().displayImage(bean.getSender().getAvatar(), holder.iv_avator);
            }

            if (bean.getSender() != null) {

                if (!StringUtil.isEmpty(bean.getSender().getNick())) {
                    holder.tv_name.setText(bean.getSender().getNick());
                }
            }

            holder.tv_content.setText(bean.getContent());

            if (bean.getImages() != null && bean.getImages().size() > 0) {
                holder.gv_gridView.setVisibility(View.VISIBLE);
                if (bean.getImages().size() == 4) {
                    holder.gv_gridView.setNumColumns(2);
                } else {
                    holder.gv_gridView.setNumColumns(3);
                }
                holder.gv_gridView.setAdapter(new MyGridAdapter(bean.getImages(), mContext));

                if (isClickable) {
                    holder.gv_gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                        @Override
                        public void onItemClick(AdapterView<?> parent,
                                                View view, int position, long id) {

                        }
                    });
                }
            } else {
                holder.gv_gridView.setVisibility(View.GONE);
            }

            if (bean.getComments() != null && bean.getComments().size() > 0) {
                holder.comment_reply_layout.setVisibility(View.VISIBLE);
                CommentAdapter commentAdapter = new CommentAdapter(mContext, bean.getComments());
                holder.comment_list.setAdapter(commentAdapter);
            } else {
                holder.comment_reply_layout.setVisibility(View.GONE);
            }
        }

        return convertView;
    }

    /**
     * 关于展示内容的逻辑
     */
    private void showContent(final ViewHolder hv, final String content,
                             final int position) {
        hv.tv_content.setText(content);
        if (availableTextWidth < StringUtil.getTextViewLength(hv.tv_content,
                content)) {
            hv.tv_showorhide.setVisibility(View.VISIBLE);
            if (!getItem(position).isShow()) {
                hv.tv_showorhide.setText("全文");
                hv.tv_showorhide.setVisibility(View.VISIBLE);
                hv.tv_content.setEllipsize(TruncateAt.END);
                hv.tv_content.setMaxLines(2);
            } else {
                hv.tv_content.setMaxLines(1024);
                hv.tv_showorhide.setText("收起");
            }

            hv.tv_showorhide.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (hv.tv_showorhide.getText().equals("全文")) {
                        hv.tv_content.setMaxLines(1024);
                        hv.tv_showorhide.setText("收起");
                        getItem(position).setIsShow(true);
                    } else {
                        hv.tv_showorhide.setText("全文");
                        hv.tv_content.setMaxLines(2);
                        hv.tv_showorhide.setVisibility(View.VISIBLE);
                        getItem(position).setIsShow(false);
                    }
                }
            });
        } else {
            hv.tv_showorhide.setVisibility(View.GONE);
        }
    }

    private static class ViewHolder {

        TextView tv_name;
        ImageView iv_avator;
        TextView tv_content;
        TextView tv_showorhide;
        MyGridView gv_gridView;
        MyListView comment_list;
        FrameLayout comment_reply_layout;

        public ViewHolder(View convertView) {
            iv_avator = (ImageView) convertView.findViewById(R.id.iv_avator);
            tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            tv_content = (TextView) convertView.findViewById(R.id.tv_content);
            tv_showorhide = (TextView) convertView.findViewById(R.id.tv_showorhide);
            gv_gridView = (MyGridView) convertView.findViewById(R.id.gv_gridView);
            comment_list = (MyListView) convertView.findViewById(R.id.comment_list);
            comment_reply_layout = (FrameLayout) convertView.findViewById(R.id.comment_reply_layout);

        }
    }

}
