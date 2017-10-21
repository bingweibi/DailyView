package com.example.bbw.openzz;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.bbw.openzz.adapter.FragmentOneAdapter;

public class MainActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private TabLayout mTabBottomLayout;
    private ViewPager mViewPager;
    private FragmentOneAdapter mFragmentOneAdapter;

    private TabLayout.Tab mTabOne;
    private TabLayout.Tab mTabTwo;
    private TabLayout.Tab mTabThree;
    private TabLayout.Tab mTabFour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化视图
        initViews();
    }

    private void initViews() {

        //使用适配器蒋ViewPager与Fragment绑定在一起
        mViewPager = findViewById(R.id.viewPager);
        mFragmentOneAdapter = new FragmentOneAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mFragmentOneAdapter);

        //将TabLayout与ViewPager绑定在一起
        mTabLayout = findViewById(R.id.tabLayout);
        mTabLayout.setupWithViewPager(mViewPager);

        //指定Tab的位置
        mTabOne = mTabLayout.getTabAt(0);
        mTabTwo = mTabLayout.getTabAt(1);
        mTabThree = mTabLayout.getTabAt(2);
        mTabFour = mTabLayout.getTabAt(3);
    }
}
