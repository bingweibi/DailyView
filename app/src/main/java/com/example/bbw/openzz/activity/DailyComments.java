package com.example.bbw.openzz.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bbw.openzz.Model.ZhiHuDaily.StoryComments;
import com.example.bbw.openzz.R;
import com.example.bbw.openzz.adapter.DailyCommentsAdapter;
import com.example.bbw.openzz.util.HttpUntil;
import com.example.bbw.openzz.util.ResponseHandleUtility;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class DailyComments extends AppCompatActivity {

    private int storyId;
    private List<StoryComments.CommentBean> storyComments;
    private String storyShortCommentsAddress;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_comments);

        Intent intent = getIntent();
        storyId = intent.getIntExtra("storyID",0);
        storyComments = new ArrayList<>();
        mListView = findViewById(R.id.listView);
        storyShortCommentsAddress = "https://news-at.zhihu.com/api/4/story/" + storyId +"/short-comments";

        HttpUntil.sendOkHttpRequest(storyShortCommentsAddress, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(getApplicationContext(),"404....",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                showStoryComments(responseText);
            }
        });
    }

    private void showStoryComments(String responseText) {

        try {
            storyComments = ResponseHandleUtility.handStoryComments(responseText);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    DailyCommentsAdapter adapter = new DailyCommentsAdapter(getApplicationContext(),R.layout.dailycomment_item,storyComments);
                    mListView.setAdapter(adapter);
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
