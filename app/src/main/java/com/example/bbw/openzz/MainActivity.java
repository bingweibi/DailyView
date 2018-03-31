package com.example.bbw.openzz;

/**
 * @author bibingwei
 */

import android.graphics.PixelFormat;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.bbw.openzz.adapter.ViewPagerAdapter;
import com.example.bbw.openzz.fragments.FragmentFour;
import com.example.bbw.openzz.fragments.FragmentOne;
import com.example.bbw.openzz.fragments.FragmentThree;
import com.example.bbw.openzz.fragments.FragmentTwo;
import com.example.bbw.openzz.util.BottomNavigationViewHelper;

public class MainActivity extends AppCompatActivity  {

    private ViewPager mViewPager;
    private MenuItem mMenuItem;
    private BottomNavigationView mBottomNavigationView;
    private long mExitTime = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //闪屏
        getWindow().setFormat(PixelFormat.TRANSLUCENT);

        mViewPager = findViewById(R.id.fragment_ViewPager);
        mBottomNavigationView = findViewById(R.id.bottom_navigation);
        BottomNavigationViewHelper.disableShiftMode(mBottomNavigationView);
    }

    @Override
    protected void onResume() {
        super.onResume();

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
        adapter.addFragment(new FragmentThree());
        adapter.addFragment(new FragmentFour());
        mViewPager.setAdapter(adapter);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
          if ((System.currentTimeMillis() - mExitTime) > 2000) {
              // 如果两次按键时间间隔大于2000毫秒，则不退出
              Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
              // 更新mExitTime
              mExitTime = System.currentTimeMillis();
          } else {
              // 否则退出程序
              System.exit(0);
          }
          return true;
      }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}
