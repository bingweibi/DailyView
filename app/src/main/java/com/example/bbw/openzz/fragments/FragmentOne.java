package com.example.bbw.openzz.fragments;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.bbw.openzz.Model.ZhiHuDailyHot.ZhiHuDailyHot;
import com.example.bbw.openzz.Model.ZhiHuDailyLatest.ZhiHuDailyLatest;
import com.example.bbw.openzz.R;
import com.example.bbw.openzz.adapter.FragmentAdapter;
import com.example.bbw.openzz.fragmentTab.FragmentTab;
import com.example.bbw.openzz.fragmentTab.FragmentOneTab;
import com.example.bbw.openzz.util.HttpUntil;
import com.example.bbw.openzz.util.ResponseHandleUtility;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static com.example.bbw.openzz.api.ZhiHuDailyApi.daily_freeTalk_url;
import static com.example.bbw.openzz.api.ZhiHuDailyApi.daily_hot_url;
import static com.example.bbw.openzz.api.ZhiHuDailyApi.daily_old_url;
import static com.example.bbw.openzz.api.ZhiHuDailyApi.daily_url;

/**
 * Created by bbw on 2017/10/21.
 * @author bibingwei
 */

public class FragmentOne extends Fragment implements ViewPager.OnPageChangeListener{

    private View mView;
    private RadioGroup mRadioGroup;
    private ViewPager mViewPager;
    private HorizontalScrollView mHorizontalScrollView;
    private List<Fragment> fragmentList = new ArrayList<>();
    private FragmentAdapter mFragmentAdapter;
    private CardView mCardView;
    private ImageView mImageView;
    private TextView mTextView;
    private LinearLayout mCardViewLinearLayout;
    private LinearLayout mLinearLayout;
    private List<ZhiHuDailyLatest.StoryBean> storiesList;
    private List<ZhiHuDailyHot.Recent> hotStoriesList;
    private View fragmentContentParent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (mView == null){
            //初始化view
            mView = inflater.inflate(R.layout.fragment,container,false);
            mRadioGroup = mView.findViewById(R.id.fragment_RadioGroup);
            mViewPager = mView.findViewById(R.id.fragment_ViewPager);
            mHorizontalScrollView = mView.findViewById(R.id.fragment_HorizontalScrollView);
            //设置RadioGroup点击事件
            mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                    mViewPager.setCurrentItem(i);
                }
            });

            //初始化顶部导航栏
            initTab(inflater);
            //初始化viewPager
            initView();
        }

        /**
         * 底部导航栏切换后，由于没有销毁顶部设置导致如果没有重新设置view
         * 导致底部切换后切回顶部页面数据会消失bug
         * 一下设置每次重新创建view即可
         */
        ViewGroup parent = (ViewGroup) mView.getParent();
        if (parent != null){
            parent.removeView(mView);
        }
        return mView;
    }

    /**
     * 初始化viewPager
     */
    private void initView( ) {

        List<FragmentTab> fragmentTabs = FragmentOneTab.getSelected();
        for(int i = 0; i< fragmentTabs.size(); i++){
            FragmentsContent mFragmentsContent = new FragmentsContent();
            fragmentList.add(mFragmentsContent);
        }

        //设置viewPager适配器
        mFragmentAdapter = new FragmentAdapter(getChildFragmentManager(),fragmentList);
        mViewPager.setAdapter(mFragmentAdapter);
        //不进行预加载
        mViewPager.setOffscreenPageLimit(0);
        //设置默认
        mViewPager.setCurrentItem(0);
        requestMessage(daily_url);
        mViewPager.addOnPageChangeListener(this);
    }

    /**
     *  初始化头部导航栏
     */
    private void initTab(LayoutInflater inflater) {

        List<FragmentTab> fragmentTabs = FragmentOneTab.getSelected();
        for (int i = 0; i< fragmentTabs.size(); i++){
            //设置头部项布局初始化数据
            RadioButton mRadioButton = (RadioButton) inflater.inflate(R.layout.fragment_tag_radiobutton,null);
            mRadioButton.setId(i);
            mRadioButton.setText(fragmentTabs.get(i).getName());
            RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(WRAP_CONTENT,WRAP_CONTENT);
            //加入RadioGroup
            mRadioGroup.addView(mRadioButton,params);
        }
        //默认点击
        mRadioGroup.check(0);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setTab(position);
        chooseUrl(position);
    }

    /**
     * 选择请求的tag
     * @param position
     */
    private void chooseUrl(int position) {

        //选择的标签
        String url = null;
        if (position == 0){
            url = daily_url;
        }else if(position == 1){
            url = daily_hot_url;
        }else if(position == 2){
            url = daily_freeTalk_url;
        }else{
            url = daily_old_url;
        }
        requestMessage(url);
    }

    /**
     * 页面跳转切换头部偏移设置
     * @param position
     */
    private void setTab(int position) {

        RadioButton mRadioButton = (RadioButton) mRadioGroup.getChildAt(position);
        //设置标题被点击
        mRadioButton.setChecked(true);
        //偏移设置
        int left = mRadioButton.getLeft();
        int width = mRadioButton.getMeasuredWidth();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screenWidth = displayMetrics.widthPixels;
        //移动距离 = 左边距离+Button宽度的一半-屏幕宽度的一半
        int len = left - width/2 - screenWidth/2;
        //移动
        mHorizontalScrollView.smoothScrollTo(len,0);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * 显示返回的内容
     * @param url
     */
    public void requestMessage(final String url) {

        HttpUntil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (url.equals("https://news-at.zhihu.com/api/3/news/hot")){
                    final String responseText = response.body().string();
                    showHotStories(responseText);
                }else{
                    final String responseText = response.body().string();
                    showStories(responseText);
                }

            }
        });
    }

    private void showHotStories(String responseText) {

        try {
            hotStoriesList  = ResponseHandleUtility.handleZhiHuDailyHot(responseText);
            if (getActivity() == null){
                return;
            }
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    for (int i=0;i<hotStoriesList.size();i++){
                        mCardView = new CardView(getContext());
                        mCardView.setUseCompatPadding(true);
                        mCardView.setCardElevation(20);
                        mCardView.setRadius(15);
                        mCardView.setClickable(true);
                        mCardViewLinearLayout = new LinearLayout(getContext());
                        mCardViewLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
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
                        mCardViewLinearLayout.addView(mImageView);
                        mCardViewLinearLayout.addView(mTextView);
                        mCardView.addView(mCardViewLinearLayout);
                        mLinearLayout.addView(mCardView);
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void showStories(String responseStories) {
        try {
            storiesList  = ResponseHandleUtility.handleZhuHuDailyLatest(responseStories);
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            fragmentContentParent = layoutInflater.inflate(R.layout.fragment_content,null);
            mLinearLayout = fragmentContentParent.findViewById(R.id.fragment_contentLayout);
            if (getActivity() == null){
                return;
            }
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    for (int i=0;i<storiesList.size();i++){
                        mCardView = new CardView(getContext());
                        mCardView.setUseCompatPadding(true);
                        mCardView.setCardElevation(20);
                        mCardView.setRadius(15);
                        mCardView.setClickable(true);
                        mCardViewLinearLayout = new LinearLayout(getContext());
                        mCardViewLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
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
                        mCardViewLinearLayout.addView(mImageView);
                        mCardViewLinearLayout.addView(mTextView);
                        mCardView.addView(mCardViewLinearLayout);
                        mLinearLayout.addView(mCardView);
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
