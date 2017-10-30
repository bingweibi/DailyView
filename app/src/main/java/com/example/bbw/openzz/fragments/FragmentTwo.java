package com.example.bbw.openzz.fragments;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.bbw.openzz.R;
import com.example.bbw.openzz.adapter.FragmentAdapter;
import com.example.bbw.openzz.fragmentTab.FragmentOneTab;
import com.example.bbw.openzz.fragmentTab.FragmentTab;
import com.example.bbw.openzz.fragmentTab.FragmentTwoTab;

import java.util.ArrayList;
import java.util.List;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * Created by bbw on 2017/10/21.
 * @author bibingwei
 */

public class FragmentTwo extends Fragment implements ViewPager.OnPageChangeListener {

    private View mView;
    private RadioGroup mRadioGroup;
    private ViewPager mViewPager;
    private HorizontalScrollView mHorizontalScrollView;
    private List<Fragment> fragmentList = new ArrayList<Fragment>();
    private FragmentAdapter mFragmentAdapter;

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
            Bundle bundle = new Bundle();
            bundle.putString("name", fragmentTabs.get(i).getName());
            mFragmentsContent.setArguments(bundle);
            fragmentList.add(mFragmentsContent);
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
        List<FragmentTab> fragmentTabs = FragmentTwoTab.getSelected();
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
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setTab(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
