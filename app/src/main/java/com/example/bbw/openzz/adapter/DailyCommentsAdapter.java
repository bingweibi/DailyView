package com.example.bbw.openzz.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bbw.openzz.Model.ZhiHuDaily.StoryComments;
import com.example.bbw.openzz.R;

import java.util.List;

/**
 * Created by bbw on 2017/11/9.
 * @author bbw
 */

public class DailyCommentsAdapter extends ArrayAdapter<StoryComments.CommentBean>{

    /**
     * 子项布局id
     */
    private int resourceId;

    public DailyCommentsAdapter(@NonNull Context context, int resource, List<StoryComments.CommentBean> objects) {
        super(context, resource,objects);
        this.resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        
        //获得当前项的Fruit实例
        StoryComments.CommentBean commentBean = getItem(position);
        View view;
        ViewHolder viewHolder;
        
        if (convertView == null){
            //inflate出子项布局，实例化其中的图片空间和文本控件
            view = LayoutInflater.from(getContext()).inflate(resourceId,null);
            viewHolder = new ViewHolder();
            viewHolder.commentsImage = view.findViewById(R.id.commentPic);
            viewHolder.commentsAuthorName = view.findViewById(R.id.commentsAuthor);
            viewHolder.commentsContent = view.findViewById(R.id.comments);
            view.setTag(viewHolder);
        }else{
            view = convertView;
            viewHolder = (ViewHolder)view.getTag();
        }
        Glide.with(getContext()).load(commentBean.getAvatar()).into(viewHolder.commentsImage);
        viewHolder.commentsAuthorName.setText(commentBean.getAuthor()+":");
        viewHolder.commentsContent.setText(commentBean.getContent());
        return view;
    }

    class ViewHolder{
        ImageView commentsImage;
        TextView commentsAuthorName;
        TextView commentsContent;
    }
}
