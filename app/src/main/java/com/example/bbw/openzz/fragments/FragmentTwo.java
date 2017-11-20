package com.example.bbw.openzz.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.bbw.openzz.Model.Gank.Gank;
import com.example.bbw.openzz.R;
import com.example.bbw.openzz.activity.PicDetail;
import com.example.bbw.openzz.adapter.GankPicAdapter;
import com.example.bbw.openzz.util.Event;
import com.example.bbw.openzz.util.HttpUntil;
import com.example.bbw.openzz.util.ResponseHandleUtility;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static com.example.bbw.openzz.api.GankApi.gankPic;


/**
 * Created by bbw on 2017/10/21.
 * @author bibingwei
 * Gank
 */

public class FragmentTwo extends Fragment {

    private List<Gank.results> responsePicList;
    private List<Gank.results> showPicList = new ArrayList<>();
    private RefreshLayout mRefreshLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View mView = inflater.inflate(R.layout.fragment_two,container,false);
        mRefreshLayout = mView.findViewById(R.id.refreshLayout);
        RecyclerView mRecyclerView = mView.findViewById(R.id.fragment_recyclerView);

        mRefreshLayout.setRefreshFooter(new BallPulseFooter(getContext()).setSpinnerStyle(SpinnerStyle.Scale));
        mRefreshLayout.setRefreshHeader(new MaterialHeader(getContext()).setShowBezierWave(true));
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(1800);
                requestPic(gankPic);
            }
        });

        StaggeredGridLayoutManager mStaggeredGridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mStaggeredGridLayoutManager);
        GankPicAdapter picAdapter = new GankPicAdapter(showPicList,getContext());
        mRecyclerView.setAdapter(picAdapter);
        picAdapter.setClickListener(new GankPicAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(getContext(),PicDetail.class);
                EventBus.getDefault().postSticky(new Event(showPicList,position));
                startActivity(intent);
            }
        });
        picAdapter.notifyDataSetChanged();
        return mView;
    }

    public void requestPic(String address) {

        HttpUntil.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Looper.prepare();
                Toast.makeText(getContext(),"网络出现故障...", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                initPic(responseText);
            }
        });
    }

    private void initPic(String responseText) {
        try {
            responsePicList = ResponseHandleUtility.handleGank(responseText);
            showPicList.clear();
            for (int i =0;i<responsePicList.size();i++){
                Gank.results pic = new Gank.results(responsePicList.get(i).getUrl(),responsePicList.get(i).getDesc(),responsePicList.get(i).getWho());
                showPicList.add(pic);
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
