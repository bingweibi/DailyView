package com.example.bbw.openzz.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.bbw.openzz.Model.ZhiHuDailyLatest.ZhiHuDailyLatest;
import com.example.bbw.openzz.R;
import com.example.bbw.openzz.util.Event;
import com.example.bbw.openzz.util.HttpUntil;
import com.example.bbw.openzz.util.ResponseHandleUtility;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by bbw on 2017/10/23.
 * @author bibingwei
 * 点击标签显示对应的内容
 */

public class FragmentsContent extends Fragment{

    /**
     * 点击的标签
     */
    private CardView mCardView;
    private ImageView mImageView;
    private TextView mTextView;
    private LinearLayout mLinearLayoutParents;
    private LinearLayout mLinearLayout;
    private List<ZhiHuDailyLatest.StoryBean> storiesList;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EventBus.getDefault().register(this);
        requestMessage("http://news-at.zhihu.com/api/4/news/latest");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_content,container,false);
        mLinearLayoutParents = view.findViewById(R.id.fragment_contentParents);
        mLinearLayout = view.findViewById(R.id.fragment_contentLayout);
        return view;
    }

    /**
     * 显示返回的内容
     * @param url
     */
    public void requestMessage(String url) {

        HttpUntil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String responseText = response.body().string();
                showStories(responseText);
                //EventBus.getDefault().post(responseText);
            }
        });
    }

//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onMessageEvent(Event event){
//
//        String responseStories = event.getResponseMessage();
//        showStories(responseStories);
//
//    }

    private void showStories(String responseStories) {
        try {
            storiesList  = ResponseHandleUtility.handleZhuHuDailyLatest(responseStories);
            if (getActivity() == null){
                return;
            }
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
//                    ViewGroup parent = (ViewGroup) mCardView.getParent();
//                    if (parent != null){
//                        parent.removeAllViews();
//                    }
                    for (int i=0;i<storiesList.size();i++){
//                        mCardView = new CardView(getContext());
//                        mCardView.setCardBackgroundColor(Color.BLUE);
                        mTextView = new TextView(getContext());
                        mImageView = new ImageView(getContext());
                        mTextView.setTextSize(18);
                        mImageView.setLayoutParams(new LinearLayout.LayoutParams(250,250));
                        mTextView.setText(storiesList.get(i).getTitle());
                        Glide.with(getActivity())
                                .load(storiesList.get(i).getImages().get(0))
                                .apply(new RequestOptions().transforms(new CenterCrop(),new RoundedCorners(2)))
                                .into(mImageView);
//                        mCardView.addView(mTextView);
//                        mCardView.addView(mImageView);
                        mLinearLayout.addView(mTextView);
                        mLinearLayout.addView(mImageView);
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        //EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
