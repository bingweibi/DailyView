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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bbw.openzz.Gson.ZhiHuDailyLatest.ZhiHuDailyLatest;
import com.example.bbw.openzz.R;
import com.example.bbw.openzz.adapter.FragmentAdapter;
import com.example.bbw.openzz.fragmentTab.FragmentTab;
import com.example.bbw.openzz.fragmentTab.FragmentOneTab;
import com.example.bbw.openzz.util.HttpUntil;
import com.example.bbw.openzz.util.ResponseHandleUtility;

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
    private TextView mTextView;
    private ImageView mImageView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (mView == null){

            //初始化view
            mView = inflater.inflate(R.layout.fragment,container,false);
            mRadioGroup = mView.findViewById(R.id.fragment_RadioGroup);
            mViewPager = mView.findViewById(R.id.fragment_ViewPager);
            mHorizontalScrollView = mView.findViewById(R.id.fragment_HorizontalScrollView);
            mCardView = mView.findViewById(R.id.fragmentContent_CardView);
            mTextView = mView.findViewById(R.id.fragmentContent_CardViewText);
            mImageView = mView.findViewById(R.id.fragmentContent_CardViewImage);
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
            FragmentsTabView mFragmentsTabView = new FragmentsTabView();
            Bundle bundle = new Bundle();
            bundle.putString("name", fragmentTabs.get(i).getName());
            mFragmentsTabView.setArguments(bundle);
            fragmentList.add(mFragmentsTabView);
        }

        //设置viewPager适配器
        mFragmentAdapter = new FragmentAdapter(getActivity().getSupportFragmentManager(),fragmentList);
        mViewPager.setAdapter(mFragmentAdapter);
        //两个viewPager切换不重新加载
        mViewPager.setOffscreenPageLimit(1);
        //设置默认
        mViewPager.setCurrentItem(0);
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
     * 显示返回的内容
     * @param url
     */
    private void requestMessage(String url) {

        HttpUntil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(getActivity(),"请求内容失败",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

//                try {
                    final String responseText = response.body().toString();
                    final List<ZhiHuDailyLatest.Stories> storiesList = ResponseHandleUtility.handleZhuHuDailyLatest(responseText);
//                    getActivity().runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            if (storiesList !=null){
//                                //先进行缓存
//                                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(getActivity()).edit();
//                                editor.putString("stories",responseText);
//                                editor.apply();
//                                //显示图片和简要内容
//                                showStoriesInfo(storiesList);
//                            }else {
//                                Toast.makeText(getActivity(),"获取失败",Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    });
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
            }
        });

    }

    /**
     * 显示内容和图片
     */
    private void showStoriesInfo(ZhiHuDailyLatest storiesList) {

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
}
