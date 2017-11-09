package com.example.bbw.openzz.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bbw.openzz.Model.ZhiHuDaily.ZhiHuDaily;
import com.example.bbw.openzz.R;

import java.util.List;

/**
 * Created by bbw on 2017/11/6.
 * @author bbw
 */

public class DailyAdapter extends RecyclerView.Adapter<DailyAdapter.ViewHolder>{

    private List<ZhiHuDaily.StoryBean> mDailyList;
    private Context mContext;
    private OnItemClickListener clickListener;

    public void setClickListener(OnItemClickListener clickListener){
        this.clickListener = clickListener;
    }

    public static interface OnItemClickListener{
        void onClick(View view,int position);
    }

     class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView dailyImage;
        TextView dailyTitle;
        View dailyView;

        public ViewHolder(View itemView) {
            super(itemView);
            dailyView = itemView;
            dailyImage = itemView.findViewById(R.id.daily_image);
            dailyTitle = itemView.findViewById(R.id.daily_title);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view){
            if (clickListener != null) {
                clickListener.onClick(itemView, getAdapterPosition());
            }
        }
    }

    public DailyAdapter(Context mContext,List<ZhiHuDaily.StoryBean> dailyList){
        mDailyList = dailyList;
        this.mContext = mContext;
    }

    @Override
    public DailyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.daily_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(DailyAdapter.ViewHolder holder, int position) {

        ZhiHuDaily.StoryBean daily = mDailyList.get(position);
        Glide.with(mContext).load(daily.getImages()).into(holder.dailyImage);
        holder.dailyTitle.setText(daily.getTitle());
    }

    @Override
    public int getItemCount() {

        return mDailyList.size();
    }
}
