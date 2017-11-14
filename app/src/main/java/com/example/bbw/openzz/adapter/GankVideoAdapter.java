package com.example.bbw.openzz.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bbw.openzz.Model.Gank.Gank;
import com.example.bbw.openzz.R;

import java.util.List;

import cn.jzvd.JZVideoPlayer;

/**
 * Created by bbw on 2017/11/14.
 * @author bbw
 */

public class GankVideoAdapter extends RecyclerView.Adapter<GankVideoAdapter.ViewHolder>{

    private List<Gank.results> mVideoList;
    private Context mContext;
    private GankVideoAdapter.OnItemClickListener clickListener;

    public GankVideoAdapter(List<Gank.results> mResults, Context mContext) {
        this.mVideoList = mResults;
        this.mContext = mContext;
    }

    public void setClickListener(GankVideoAdapter.OnItemClickListener clickListener){
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
    public GankVideoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_item, parent, false);
        final ViewHolder mViewHolder = new ViewHolder(view);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(GankVideoAdapter.ViewHolder holder, int position) {

        Gank.results results = mVideoList.get(position);
        holder.gankVideo.setUp(results.getUrl(), JZVideoPlayer.SCREEN_WINDOW_NORMAL,results.getDesc());
    }

    @Override
    public int getItemCount() {
        return mVideoList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        cn.jzvd.JZVideoPlayerStandard gankVideo;
        View gankVideoView;

        public ViewHolder(View itemView) {
            super(itemView);
            gankVideoView = itemView;
            gankVideo = itemView.findViewById(R.id.video_player);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null){
                clickListener.onClick(itemView,getAdapterPosition());
            }
        }
    }
}
