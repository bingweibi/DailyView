package com.example.bbw.openzz;

/**
 * @author bibingwei
 */

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.example.bbw.openzz.adapter.FragmentOneAdapter;
import com.example.bbw.openzz.tabRes.TabRes;

public class MainActivity extends AppCompatActivity implements TabHost.OnTabChangeListener {

    private FragmentTabHost mFragmentTabHost;
//    private TabLayout mTabLayout;
//    private ViewPager mViewPager;
//    private FragmentOneAdapter mFragmentOneAdapter;
//
//    private TabLayout.Tab mTabOne;
//    private TabLayout.Tab mTabTwo;
//    private TabLayout.Tab mTabThree;
//    private TabLayout.Tab mTabFour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化视图
        //initViews();
        //初始化FragmentTabHost
        initHost();
        //初始化底部导航栏
        initTab();
        //默认选中日报
        mFragmentTabHost.onTabChanged(TabRes.getTabText()[0]);
    }

    private void initTab() {

        String[] tabs = TabRes.getTabText();
        for (int i=0; i<tabs.length; i++){
            //新建TabSpec
            TabHost.TabSpec tabSpec = mFragmentTabHost.newTabSpec(TabRes.getTabText()[i]).setIndicator(TabRes.getTabText()[i]);
            //设置view
            View view = LayoutInflater.from(this).inflate(R.layout.bottom_tab_foot,null);
            ((TextView)view.findViewById(R.id.bottom_tab_text)).setText(TabRes.getTabText()[i]);
            //加入tabSpec
            mFragmentTabHost.addTab(tabSpec,TabRes.getFragment()[i],null);
        }
    }

    private void initHost() {

        mFragmentTabHost = findViewById(R.id.bottom_tab);
        //调用setup方法，设置view
        mFragmentTabHost.setup(this,getSupportFragmentManager(),R.id.main_content);
        //去除分割线
        mFragmentTabHost.getTabWidget().setDividerDrawable(null);
        //监听事件
        mFragmentTabHost.setOnTabChangedListener(this);
    }

//    private void initViews() {
//
//        //使用适配器蒋ViewPager与Fragment绑定在一起
//        mViewPager = findViewById(R.id.viewPager);
//        mFragmentOneAdapter = new FragmentOneAdapter(getSupportFragmentManager());
//        mViewPager.setAdapter(mFragmentOneAdapter);
//
//        //将TabLayout与ViewPager绑定在一起
//        mTabLayout = findViewById(R.id.tabLayout);
//        mTabLayout.setupWithViewPager(mViewPager);
////        mTabBottomLayout.setupWithViewPager(mViewPager);
//
//        //指定Tab的位置
//        mTabOne = mTabLayout.getTabAt(0);
//        mTabTwo = mTabLayout.getTabAt(1);
//        mTabThree = mTabLayout.getTabAt(2);
//        mTabFour = mTabLayout.getTabAt(3);
//    }

    @Override
    public void onTabChanged(String s) {
        //从分割线中获得多少个切换界面
        TabWidget mTabWidget = mFragmentTabHost.getTabWidget();
        for (int i =0; i<mTabWidget.getChildCount();i++){
            View view = mTabWidget.getChildAt(i);
            TextView mTextView = view.findViewById(R.id.bottom_tab_text);
        }
    }
}
