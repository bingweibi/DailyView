package com.example.bbw.openzz.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.example.bbw.openzz.Model.Gank.Gank;
import com.example.bbw.openzz.R;
import com.example.bbw.openzz.adapter.GankPicDetailAdapter;
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
        mLinearLayoutManager.scrollToPositionWithOffset(picPosition,0);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        new LinearSnapHelper().attachToRecyclerView(mRecyclerView);
        GankPicDetailAdapter picAdapter = new GankPicDetailAdapter(showPicList,getApplicationContext());
        mRecyclerView.setAdapter(picAdapter);
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

    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
    public void onEvent(Event event){
        showPicList = event.getShowPicList();
        picPosition = event.getPosition();
    }

}
