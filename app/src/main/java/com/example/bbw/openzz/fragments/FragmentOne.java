package com.example.bbw.openzz.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.bbw.openzz.Model.ZhiHuDailyLatest.ZhiHuDailyLatest;
import com.example.bbw.openzz.R;
import com.example.bbw.openzz.adapter.DailyAdapter;
import com.example.bbw.openzz.util.HttpUntil;
import com.example.bbw.openzz.util.ResponseHandleUtility;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
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

    private List<ZhiHuDailyLatest.StoryBean> responseStoriesList;
    private List<ZhiHuDailyLatest.StoryBean> showStoriesList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View mView = inflater.inflate(R.layout.fragment_base,container,false);
        requestMessage(daily_url);
        RecyclerView mRecyclerView = mView.findViewById(R.id.fragment_recyclerView);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getContext());
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        DailyAdapter dailyAdapter = new DailyAdapter(getContext(),showStoriesList);
        mRecyclerView.setAdapter(dailyAdapter);
        return mView;
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
                initStories(responseText);
            }
        });
    }

    private void initStories(String responseStories) {
        try {
            responseStoriesList  = ResponseHandleUtility.handleZhuHuDailyLatest(responseStories);
            for(int i=0;i<responseStoriesList.size();i++){
                ZhiHuDailyLatest.StoryBean stories =
                        new ZhiHuDailyLatest.StoryBean(responseStoriesList.get(i).getTitle(),responseStoriesList.get(i).getImages(),responseStoriesList.get(i).getId());
                showStoriesList.add(stories);
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
