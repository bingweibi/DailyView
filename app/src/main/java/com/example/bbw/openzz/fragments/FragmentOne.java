package com.example.bbw.openzz.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.bbw.openzz.Model.ZhiHuDailyLatest.ZhiHuDailyLatest;
import com.example.bbw.openzz.R;
import com.example.bbw.openzz.activity.DailyDetail;
import com.example.bbw.openzz.util.HttpUntil;
import com.example.bbw.openzz.util.ResponseHandleUtility;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static com.example.bbw.openzz.api.ZhiHuDailyApi.daily_url;

/**
 * Created by bbw on 2017/10/21.
 * @author bibingwei
 */

public class FragmentOne extends Fragment {

    private LinearLayout mLinearLayout;
    private CardView mCardView;
    private LinearLayout mCardLinearLayout;
    private ImageView mImageView;
    private TextView mTextView;
    private List<ZhiHuDailyLatest.StoryBean> storiesList;
    private int i;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base,null);
        mLinearLayout = view.findViewById(R.id.fragment_content);
        requestMessage(daily_url);
//        mCardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getContext(), DailyDetail.class);
//            }
//        });
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
                Toast.makeText(getContext(),"404.....", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String responseText = response.body().string();
                showStories(responseText);
            }
        });
    }

    private void showStories(String responseStories) {
        try {
            storiesList  = ResponseHandleUtility.handleZhuHuDailyLatest(responseStories);
            if (getActivity() == null){
                return;
            }
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    for (i=0;i<storiesList.size();i++){
                        mCardView = new CardView(getContext());
                        mCardView.setId(i);
                        mCardView.setUseCompatPadding(true);
                        mCardView.setCardElevation(20);
                        mCardView.setRadius(15);
                        mCardView.setClickable(true);
                        mCardLinearLayout = new LinearLayout(getContext());
                        mCardLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
                        mTextView = new TextView(getContext());
                        mImageView = new ImageView(getContext());
                        mImageView.setPadding(15,20,0,20);
                        mTextView.setTextSize(16);
                        mTextView.setPadding(50,60,20,60);
                        mImageView.setLayoutParams(new LinearLayout.LayoutParams(280,280));
                        mTextView.setText(storiesList.get(i).getTitle());
                        Glide.with(getActivity())
                                .load(storiesList.get(i).getImages().get(0))
                                .apply(new RequestOptions().transforms(new CenterCrop(),new RoundedCorners(2)))
                                .into(mImageView);
                        mCardLinearLayout.addView(mImageView);
                        mCardLinearLayout.addView(mTextView);
                        mCardView.addView(mCardLinearLayout);
                        mLinearLayout.addView(mCardView);
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
