package com.example.bbw.openzz.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bbw.openzz.R;
import com.example.bbw.openzz.db.ZhiHuDailyBean;

import java.util.List;

/**
 * Created by bbw on 2017/11/6.
 * @author bbw
 */

public class DailyAdapter extends RecyclerView.Adapter<DailyAdapter.ViewHolder>{

    private List<ZhiHuDailyBean> mDailyList;

    static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView dailyImage;
        TextView dailyTitle;
        View dailyView;

        public ViewHolder(View itemView) {
            super(itemView);
            dailyView = itemView;
            dailyImage = itemView.findViewById(R.id.daily_image);
            dailyTitle = itemView.findViewById(R.id.daily_title);
        }
    }

    public DailyAdapter(List<ZhiHuDailyBean> dailyList){
        mDailyList = dailyList;
    }

    @Override
    public DailyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.daily_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(DailyAdapter.ViewHolder holder, int position) {

        ZhiHuDailyBean daily = mDailyList.get(position);
        //holder.dailyImage.setImageResource(daily.getImageUrl());
        holder.dailyTitle.setText(daily.getContentUrl());
    }

    @Override
    public int getItemCount() {
        return mDailyList.size();
    }
}
