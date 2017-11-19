package com.example.bbw.openzz.fragments;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.bbw.openzz.Model.Gank.Gank;
import com.example.bbw.openzz.R;
import com.example.bbw.openzz.util.HttpUntil;
import com.example.bbw.openzz.util.ResponseHandleUtility;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static com.example.bbw.openzz.api.VideoApi.videoURL;

/**
 * Created by bbw on 2017/11/14.
 * @author bibingwei
 */

public class FragmentThree extends Fragment{

    private List<Gank.results> responseVideoList;
    private List<Gank.results> showVideoList = new ArrayList<>();
    private RefreshLayout mRefreshLayout;
    private VideoView mVideoView;
    private Button mButton;
    private TextView mTextView;
    private TextView mTextViewAuthor;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestVideo(videoURL);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View mView = inflater.inflate(R.layout.fragment_three,container,false);
        mRefreshLayout = mView.findViewById(R.id.refreshLayout);
        mVideoView = mView.findViewById(R.id.video_player);
        mTextView = mView.findViewById(R.id.videoTitle);
        mTextViewAuthor = mView.findViewById(R.id.videoAuthor);
        mButton = mView.findViewById(R.id.start);

        mRefreshLayout.setRefreshFooter(new BallPulseFooter(getContext()).setSpinnerStyle(SpinnerStyle.Scale));
        mRefreshLayout.setRefreshHeader(new MaterialHeader(getContext()).setShowBezierWave(true));
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(1800);
                requestVideo(videoURL);
            }
        });

        mButton.setText("开始");
        mVideoView.requestFocus();
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mVideoView.setVideoURI(Uri.parse(showVideoList.get(0).getUrl()));
                mVideoView.setVideoURI(Uri.parse("http://ic.snssdk.com/neihan/video/playback/?video_id=cbe1506a33564968be703c8dfdbf6e63&quality=480p&line=0&is_gif=0&device_platform=.mp4"));
                mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mVideoView.start();
                    }
                });
            }
        });
        return mView;
    }

    private void requestVideo(String address) {

        HttpUntil.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Looper.prepare();
                Toast.makeText(getContext(),"404.....", Toast.LENGTH_SHORT).show();
                Looper.loop();
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
                Gank.results video = new Gank.results(responseVideoList.get(i).getUrl(),responseVideoList.get(i).getDesc(),responseVideoList.get(i).getUrl());
                showVideoList.add(video);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mVideoView.pause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mVideoView.stopPlayback();
    }
}
