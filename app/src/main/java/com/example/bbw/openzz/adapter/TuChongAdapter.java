package com.example.bbw.openzz.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.bbw.openzz.Model.TuChong.TuChong;
import com.example.bbw.openzz.R;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by bbw on 2017/11/10.
 * @author bbw
 */

public class TuChongAdapter extends RecyclerView.Adapter<TuChongAdapter.ViewHolder> {

    private List<TuChong.PostList> mPicList;
    private Context mContext;
    private OnItemClickListener clickListener;

    public void setClickListener(OnItemClickListener clickListener){
        this.clickListener = clickListener;
    }

    public interface OnItemClickListener{
        /**
         * 点击接口
         * @param view
         * @param position
         */
        void onClick(View view, int position);
    }

    @Override
    public TuChongAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tuchong_item,parent,false);
        final ViewHolder mViewHolder = new ViewHolder(view);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        TuChong.PostList postList = mPicList.get(position);
        Glide.with(mContext).load(postList.getUrl()).into(holder.tuChongPic);
    }

    @Override
    public int getItemCount() {
        return mPicList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView tuChongPic;
        View tuChongView;

        public ViewHolder(View itemView) {
            super(itemView);
            tuChongView = itemView;
            tuChongPic = itemView.findViewById(R.id.tuchong_image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null){
                clickListener.onClick(itemView,getAdapterPosition());
            }
        }
    }

    public TuChongAdapter(List<TuChong.PostList> mPostList, Context mContext) {
        this.mPicList = mPostList;
        this.mContext = mContext;
    }
}
