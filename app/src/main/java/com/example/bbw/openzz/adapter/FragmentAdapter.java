package com.example.bbw.openzz.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.bbw.openzz.fragments.FragmentFour;
import com.example.bbw.openzz.fragments.FragmentOne;
import com.example.bbw.openzz.fragments.FragmentThree;
import com.example.bbw.openzz.fragments.FragmentTwo;

import java.util.List;

/**
 * Created by bbw on 2017/10/21.
 * @author bibingwei
 */

public class FragmentAdapter extends FragmentStatePagerAdapter{

    private List<Fragment> fragmentList;
    private FragmentManager fragmentManager;

    public FragmentAdapter(FragmentManager fragmentManager, List<Fragment> fragmentList) {
        super(fragmentManager);
        this.fragmentManager = fragmentManager;
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return this.fragmentList.get(position % this.fragmentList.size());
    }

    @Override
    public int getCount() {
        return this.fragmentList.size();
    }

    @Override
    public int getItemPosition(Object object){
        return -2;
    }
}
