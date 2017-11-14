package com.example.bbw.openzz.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.bbw.openzz.Model.Gank.Gank;
import com.example.bbw.openzz.R;
import com.example.bbw.openzz.activity.PicDetail;
import com.example.bbw.openzz.adapter.GankPicAdapter;
import com.example.bbw.openzz.adapter.GankVideoAdapter;
import com.example.bbw.openzz.util.Event;
import com.example.bbw.openzz.util.HttpUntil;
import com.example.bbw.openzz.util.ResponseHandleUtility;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.jzvd.JZVideoPlayer;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static com.example.bbw.openzz.api.VideoApi.gank_Video;

/**
 * Created by bbw on 2017/11/14.
 * @author bibingwei
 */

public class FragmentThree extends Fragment{

    private List<Gank.results> responseVideoList;
    private List<Gank.results> showVideoList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestVideo(gank_Video);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View mView = inflater.inflate(R.layout.fragment_three,container,false);
        RecyclerView mRecyclerView = mView.findViewById(R.id.fragment_recyclerView);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getContext());
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        GankVideoAdapter videoAdapter = new GankVideoAdapter(showVideoList,getContext());
        mRecyclerView.setAdapter(videoAdapter);
        videoAdapter.setClickListener(new GankVideoAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
//                Intent intent = new Intent(getContext(),PicDetail.class);
//                EventBus.getDefault().postSticky(new Event(showVideoList,position));
//                startActivity(intent);
            }
        });
        videoAdapter.notifyDataSetChanged();
        return mView;
    }

    @Override
    public void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }

    private void requestVideo(String address) {

        HttpUntil.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(getContext(),"404.....", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                initVideo(responseText);
            }
        });
    }

    private void initVideo(String responseText) {
        try {
            responseVideoList = ResponseHandleUtility.handleGank(responseText);
            showVideoList.clear();
            for (int i = 0; i< responseVideoList.size(); i++){
                Gank.results video = new Gank.results(responseVideoList.get(i).getUrl(),responseVideoList.get(i).getDesc());
                showVideoList.add(video);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
