package com.example.bbw.openzz.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.bbw.openzz.Model.ZhiHuDaily.ZhiHuDaily;
import com.example.bbw.openzz.R;
import com.example.bbw.openzz.activity.DailyDetail;
import com.example.bbw.openzz.adapter.DailyAdapter;
import com.example.bbw.openzz.util.HttpUntil;
import com.example.bbw.openzz.util.ResponseHandleUtility;

import org.json.JSONException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static com.example.bbw.openzz.api.ZhiHuDailyApi.daily_old_url;
import static com.example.bbw.openzz.api.ZhiHuDailyApi.daily_url;

/**
 * Created by bbw on 2017/10/21.
 * @author bibingwei
 * 知乎
 */

public class FragmentOne extends Fragment {

    private List<ZhiHuDaily.StoryBean> responseStoriesList;
    private List<ZhiHuDaily.StoryBean> showStoriesList = new ArrayList<>();
    private SwipeRefreshLayout mRefreshLayout;
    private DailyAdapter dailyAdapter;
    private RecyclerView mRecyclerView;
    private String today;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View mView = inflater.inflate(R.layout.fragment_one,container,false);
        mRefreshLayout = mView.findViewById(R.id.refreshLayout);
        mRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mRecyclerView = mView.findViewById(R.id.fragment_recyclerView);

        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getContext());
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        dailyAdapter = new DailyAdapter(getContext(),showStoriesList);
        dailyAdapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(dailyAdapter);

        today = new SimpleDateFormat("yyyyMMdd").format(new Date());
        return mView;
    }

    @Override
    public void onStart() {
        super.onStart();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String dailyString = preferences.getString("daily",null);
        if (dailyString != null){
            try {
                showStoriesList.clear();
                responseStoriesList  = ResponseHandleUtility.handleZhuHuDailyLatest(dailyString);
                for(int i=0;i<responseStoriesList.size();i++){
                    ZhiHuDaily.StoryBean stories =
                            new ZhiHuDaily.StoryBean(responseStoriesList.get(i).getTitle(),responseStoriesList.get(i).getImages(),responseStoriesList.get(i).getId());
                    showStoriesList.add(stories);
                }
                dailyAdapter.notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else{
            requestMessage(daily_url);
        }

        dailyAdapter.setClickListener(new DailyAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(getContext(), DailyDetail.class);
                intent.putExtra("storyId",showStoriesList.get(position).getId());
                startActivity(intent);
            }
        });

        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestMessage(daily_url);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * 显示返回的内容
     * @param url
     */
    public void requestMessage(String url) {

        HttpUntil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Looper.prepare();
                Toast.makeText(getContext(),"404.....", Toast.LENGTH_SHORT).show();
                Looper.loop();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mRefreshLayout.setRefreshing(false);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String responseText = response.body().string();
                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(getContext()).edit();
                editor.putString("daily",responseText);
                editor.apply();
                initStories(responseText);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mRefreshLayout.setRefreshing(false);
                    }
                });
            }
        });
    }

    private void initStories(String responseStories) {
        try {
            showStoriesList.clear();
            responseStoriesList  = ResponseHandleUtility.handleZhuHuDailyLatest(responseStories);
            for(int i=0;i<responseStoriesList.size();i++){
                ZhiHuDaily.StoryBean stories =
                        new ZhiHuDaily.StoryBean(responseStoriesList.get(i).getTitle(),responseStoriesList.get(i).getImages(),responseStoriesList.get(i).getId());
                showStoriesList.add(stories);
            }
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    dailyAdapter.notifyDataSetChanged();
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
