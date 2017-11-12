package com.example.bbw.openzz.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;
import com.example.bbw.openzz.Model.Gank.Gank;
import com.example.bbw.openzz.R;

import java.util.List;

/**
 * Created by bbw on 2017/11/12.
 * @author bbw
 */

public class GankPicAdapter extends  RecyclerView.Adapter<GankPicAdapter.PicViewHolder> {

    private List<Gank.results> mPicList;
    private Context mContext;
    private int picPosition;

    public GankPicAdapter(List<Gank.results> mResults, Context mContext,int picPosition) {
        this.mPicList = mResults;
        this.mContext = mContext;
        this.picPosition = picPosition;
    }

    @Override
    public GankPicAdapter.PicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gank_imageitem,parent,false);
        final PicViewHolder mViewHolder = new PicViewHolder(view);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(GankPicAdapter.PicViewHolder holder, int position) {

        Gank.results results = mPicList.get(position);
        Glide.with(mContext).load(results.getUrl()).into(holder.gankPic);
    }

    @Override
    public int getItemCount() {
        return mPicList.size();
    }

    class PicViewHolder extends RecyclerView.ViewHolder{

        ImageView gankPic;
        View gankView;

        public PicViewHolder(View itemView) {
            super(itemView);
            gankView = itemView;
            gankPic = itemView.findViewById(R.id.gank_image);
        }
    }
}
