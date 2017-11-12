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
 * Created by bbw on 2017/11/10.
 * @author bbw
 */

public class GankAdapter extends RecyclerView.Adapter<GankAdapter.ViewHolder> {

    private List<Gank.results> mPicList;
    private Context mContext;
    private OnItemClickListener clickListener;

    public GankAdapter(List<Gank.results> mResults, Context mContext) {
        this.mPicList = mResults;
        this.mContext = mContext;
    }

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
    public GankAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gank_item, parent, false);
        final ViewHolder mViewHolder = new ViewHolder(view);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Gank.results results = mPicList.get(position);
        Glide.with(mContext).load(results.getUrl()).into(holder.gankPic);

    }

    @Override
    public int getItemCount() {
        return mPicList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView gankPic;
        View gankView;

        public ViewHolder(View itemView) {
            super(itemView);
            gankView = itemView;
            gankPic = itemView.findViewById(R.id.gank_image);
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
