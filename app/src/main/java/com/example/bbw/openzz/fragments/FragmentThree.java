package com.example.bbw.openzz.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bbw.openzz.Model.NeihanVideo.NeihanVideo;
import com.example.bbw.openzz.R;
import com.example.bbw.openzz.util.HttpUntil;
import com.example.bbw.openzz.util.ResponseHandleUtility;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.RenderersFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static com.example.bbw.openzz.api.VideoApi.videoURL;
import static com.scwang.smartrefresh.layout.constant.DimensionStatus.Default;

/**
 * Created by bbw on 2017/11/14.
 * @author bibingwei
 */

public class FragmentThree extends Fragment{

    private List<NeihanVideo.NeihanData.NeihanVideoData.NeihanDataGroup> responseVideoList;
    private List<NeihanVideo.NeihanData.NeihanVideoData.NeihanDataGroup> showVideoList = new ArrayList<>();
    private SimpleExoPlayerView mVideoView;
    private SimpleExoPlayer mSimpleExoPlayer;
    private TextView mTextView;
    protected View mView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        try{
            //界面可见时
            if(getUserVisibleHint()){
                mSimpleExoPlayer.setPlayWhenReady(true);
                playVideo();
            }else {
                mSimpleExoPlayer.setPlayWhenReady(false);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_three,container,false);
        mVideoView = mView.findViewById(R.id.video_player);
        mTextView = mView.findViewById(R.id.videoTitle);

        requestVideo(videoURL);
        initPlayer();
        return mView;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    private void initPlayer() {
        //1. 创建一个默认的 TrackSelector
        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory videoTackSelectionFactory =
                new AdaptiveTrackSelection.Factory(bandwidthMeter);
        TrackSelector trackSelector =
                new DefaultTrackSelector(videoTackSelectionFactory);
        //2.创建ExoPlayer
        mSimpleExoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(),trackSelector);
        //3.为SimpleExoPlayer设置播放器
        mVideoView.setPlayer(mSimpleExoPlayer);
    }

    private void playVideo() {

        // 生成加载媒体数据的DataSource实例。
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(getActivity(),
                Util.getUserAgent(getActivity(),"useExoplayer"),null);
        // 生成用于解析媒体数据的Extractor实例。
        ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
        // MediaSource代表要播放的媒体。
        MediaSource videoSource = new ExtractorMediaSource(Uri.parse(showVideoList.get(0).getMp4_url()),dataSourceFactory,extractorsFactory,
                null,null);
        //Prepare the player with the source.
        mSimpleExoPlayer.prepare(videoSource);
        //添加监听的listener
        mSimpleExoPlayer.addListener(eventListener);
        mSimpleExoPlayer.setPlayWhenReady(false);
        mTextView.setText(showVideoList.get(0).getText());
    }

    private Player.EventListener eventListener = new Player.EventListener() {
        @Override
        public void onTimelineChanged(Timeline timeline, Object manifest) {
            Log.d("log","onTimelineChanged");
        }

        @Override
        public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {
            Log.d("log","onTracksChanged");
        }

        @Override
        public void onLoadingChanged(boolean isLoading) {
            Log.d("log","onLoadingChanged");
        }

        @Override
        public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {

            if (playbackState == Player.STATE_ENDED) {
            setPlayPause(true);
            mSimpleExoPlayer.seekTo(0);
            }
        }

        @Override
        public void onRepeatModeChanged(int repeatMode) {

        }

        @Override
        public void onPlayerError(ExoPlaybackException error) {
            Log.d("log","onPlaybackError: "+error.getMessage());
        }

        @Override
        public void onPositionDiscontinuity() {
            Log.d("log","onPositionDiscontinuity");
        }

        @Override
        public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
            Log.d("log","MainActivity.onPlaybackParametersChanged."+playbackParameters.toString());
        }
    };

    private void setPlayPause(boolean play){
        mSimpleExoPlayer.setPlayWhenReady(play);
    }

    public void requestVideo(String address) {

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
            responseVideoList = ResponseHandleUtility.handleVideo(responseText);
            showVideoList.clear();
            for (int i = 0; i< responseVideoList.size(); i++){
                NeihanVideo.NeihanData.NeihanVideoData.NeihanDataGroup video = new NeihanVideo.NeihanData.NeihanVideoData.NeihanDataGroup(responseVideoList.get(i).getMp4_url(),responseVideoList.get(i).getText());
                showVideoList.add(video);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPause() {
        mSimpleExoPlayer.stop();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mSimpleExoPlayer.release();
        super.onDestroy();
    }
}
