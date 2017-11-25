package com.example.bbw.openzz.activity;

import android.content.Intent;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

/**
 * @author bbw
 */
public class DailyComments extends AppCompatActivity {

    private int storyId;
    private List<StoryComments.CommentBean> storyComments;
    private String storyShortCommentsAddress;
    private ListView mListView;
    private Toolbar mToolbar;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_comments);

        Intent intent = getIntent();
        storyId = intent.getIntExtra("storyID",0);
        storyComments = new ArrayList<>();
        mToolbar = findViewById(R.id.commentsToolbar);
        mToolbar.setTitle("");
        mToolbar.setNavigationIcon(R.drawable.back);
        setSupportActionBar(mToolbar);
        mTextView = findViewById(R.id.toolbarText);
        mTextView.setText("评论");
        mListView = findViewById(R.id.listView);
        storyShortCommentsAddress = "https://news-at.zhihu.com/api/4/story/" + storyId +"/short-comments";

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DailyComments.this.finish();
            }
        });

        HttpUntil.sendOkHttpRequest(storyShortCommentsAddress, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Looper.prepare();
                Toast.makeText(getApplicationContext(),"404....",Toast.LENGTH_SHORT).show();
                Looper.loop();
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
            storyComments = ResponseHandleUtility.handleStoryComments(responseText);
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
