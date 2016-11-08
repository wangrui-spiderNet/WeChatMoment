package com.example.wechatmoment.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.wechatmoment.R;
import com.example.wechatmoment.entity.WechatMoment;

import java.util.List;

/**
 * Created by wangrui on 2016/11/8.
 */

public class CommentAdapter extends BaseAdapter {
    private Context context;
    private List<WechatMoment.CommentsEntity> commentsEntities;

    public CommentAdapter(Context context, List<WechatMoment.CommentsEntity> commentsEntities) {
        this.context = context;
        this.commentsEntities = commentsEntities;
    }

    @Override
    public int getCount() {
        return commentsEntities.size();
    }

    @Override
    public Object getItem(int position) {
        return commentsEntities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_comment_list, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        WechatMoment.CommentsEntity entity=commentsEntities.get(position);
        holder.comment_target.setText(entity.getSender().getUsername());
        holder.comment_content.setText(entity.getContent());

        return convertView;
    }

    class ViewHolder {
        TextView comment_target;
        TextView comment_content;

        public ViewHolder(View view) {
            comment_content = (TextView) view.findViewById(R.id.comment_content);
            comment_target = (TextView) view.findViewById(R.id.comment_target);
        }

    }
}
