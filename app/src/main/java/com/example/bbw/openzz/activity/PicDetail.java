package com.example.bbw.openzz.activity;

import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.bbw.openzz.Model.Gank.Gank;
import com.example.bbw.openzz.R;
import com.example.bbw.openzz.adapter.GankAdapter;
import com.example.bbw.openzz.adapter.GankPicAdapter;
import com.example.bbw.openzz.util.Event;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bbw
 */
public class PicDetail extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ImageView mImageView;
    private List<Gank.results> showPicList = new ArrayList<>();
    private int picPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_detail);

        mImageView = findViewById(R.id.gank_image);
        mRecyclerView = findViewById(R.id.gankImageRecyclerView);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        new LinearSnapHelper().attachToRecyclerView(mRecyclerView);
        GankPicAdapter picAdapter = new GankPicAdapter(showPicList,getApplicationContext(),picPosition);
        mRecyclerView.setAdapter(picAdapter);
        picAdapter.notifyDataSetChanged();
    }

    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
    public void onEvent(Event event){
        showPicList = event.getShowPicList();
        picPosition = event.getPosition();
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
