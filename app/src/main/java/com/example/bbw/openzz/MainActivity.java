package com.example.bbw.openzz;

/**
 * @author bibingwei
 */

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.MenuItem;

import com.example.bbw.openzz.adapter.ViewPagerAdapter;
import com.example.bbw.openzz.fragments.BaseFragment;
import com.example.bbw.openzz.fragments.FragmentOne;
import com.example.bbw.openzz.fragments.FragmentTwo;
import com.example.bbw.openzz.util.BottomNavigationViewHelper;

public class MainActivity extends AppCompatActivity  {

    private ViewPager mViewPager;
    private MenuItem mMenuItem;
    private BottomNavigationView mBottomNavigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = findViewById(R.id.fragment_ViewPager);
        mBottomNavigationView = findViewById(R.id.bottom_navigation);
        BottomNavigationViewHelper.disableShiftMode(mBottomNavigationView);

        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.item_daily:
                        mViewPager.setCurrentItem(0);
                        break;
                    case R.id.item_pic:
                        mViewPager.setCurrentItem(1);
                        break;
                    case R.id.item_video:
                        mViewPager.setCurrentItem(2);
                        break;
                    case R.id.item_about:
                        mViewPager.setCurrentItem(3);
                        break;
                        default:
                }
                return false;
            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(mMenuItem != null){
                    mMenuItem.setChecked(false);
                }else {
                    mBottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                mMenuItem = mBottomNavigationView.getMenu().getItem(position);
                mMenuItem.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        setUpViewPager(mViewPager);
    }

    private void setUpViewPager(ViewPager mViewPager) {

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentOne());
        adapter.addFragment(new FragmentTwo());
        adapter.addFragment(BaseFragment.newInstance("白日梦"));
        adapter.addFragment(BaseFragment.newInstance("关于"));
        mViewPager.setAdapter(adapter);
    }
}
