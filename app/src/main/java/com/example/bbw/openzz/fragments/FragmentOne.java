package com.example.bbw.openzz.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

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
 * 知乎
 */

public class FragmentOne extends Fragment {

    private List<ZhiHuDaily.StoryBean> responseStoriesList;
    private List<ZhiHuDaily.StoryBean> showStoriesList = new ArrayList<>();
    private RefreshLayout mRefreshLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestMessage(daily_url);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View mView = inflater.inflate(R.layout.fragment_one,container,false);
        mRefreshLayout = mView.findViewById(R.id.refreshLayout);
        RecyclerView mRecyclerView = mView.findViewById(R.id.fragment_recyclerView);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        mRefreshLayout.setRefreshFooter(new BallPulseFooter(getContext()).setSpinnerStyle(SpinnerStyle.Scale));
        mRefreshLayout.setRefreshHeader(new MaterialHeader(getContext()).setShowBezierWave(true));
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(1800);
                requestMessage(daily_url);
            }
        });

        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getContext());
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        DailyAdapter dailyAdapter = new DailyAdapter(getContext(),showStoriesList);
        mRecyclerView.setAdapter(dailyAdapter);
        dailyAdapter.setClickListener(new DailyAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(getContext(), DailyDetail.class);
                intent.putExtra("storyId",showStoriesList.get(position).getId());
                startActivity(intent);
            }
        });
        dailyAdapter.notifyDataSetChanged();
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
                Looper.prepare();
                Toast.makeText(getContext(),"404.....", Toast.LENGTH_SHORT).show();
                Looper.loop();
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
            showStoriesList.clear();
            for(int i=0;i<responseStoriesList.size();i++){
                ZhiHuDaily.StoryBean stories =
                        new ZhiHuDaily.StoryBean(responseStoriesList.get(i).getTitle(),responseStoriesList.get(i).getImages(),responseStoriesList.get(i).getId());
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
