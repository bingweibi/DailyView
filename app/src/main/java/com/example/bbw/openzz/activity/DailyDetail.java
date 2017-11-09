package com.example.bbw.openzz.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bbw.openzz.Model.ZhiHuDaily.StoryDetail;
import com.example.bbw.openzz.R;
import com.example.bbw.openzz.util.HtmlUtil;
import com.example.bbw.openzz.util.HttpUntil;
import com.example.bbw.openzz.util.ResponseHandleUtility;

import org.json.JSONException;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author bbw
 */
public class DailyDetail extends AppCompatActivity {

    private String storyAddress;
    private int storyId;
    private StoryDetail storyDetail;
    private ImageView mImageView;
    private Toolbar mToolBar;
    private WebView mWebView;
    private FloatingActionButton mFloatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_detail);

        mImageView = findViewById(R.id.dailyContentImage);
        mWebView = findViewById(R.id.contentText);
        mWebView.setDrawingCacheEnabled(true);
        mToolBar = findViewById(R.id.toolBar);
        mToolBar.setNavigationIcon(R.drawable.back);
        mFloatingActionButton = findViewById(R.id.comments);

        Intent intent = getIntent();
        storyId = intent.getIntExtra("storyId",0);
        storyAddress = "https://news-at.zhihu.com/api/4/news/" + storyId;

        HttpUntil.sendOkHttpRequest(storyAddress, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(getApplicationContext(),"404...",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                showStoryDetail(responseText);
            }
        });

        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(),DailyComments.class);
                intent1.putExtra("storyID",storyId);
                startActivity(intent1);
            }
        });
    }

    private void showStoryDetail(String responseText)  {

        try {
            storyDetail  = ResponseHandleUtility.handleStoryDetail(responseText);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Glide.with(getApplicationContext()).load(storyDetail.getImage()).into(mImageView);
                    mToolBar.setTitle(storyDetail.getTitle());
                    setSupportActionBar(mToolBar);
                    String htmlData = HtmlUtil.createHtmlData(storyDetail);
                    mWebView.loadData(htmlData, HtmlUtil.MIME_TYPE, HtmlUtil.ENCODING);
                    mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            finish();
                        }
                    });
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
