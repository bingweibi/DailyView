package com.example.bbw.openzz.tabRes;

import com.example.bbw.openzz.fragments.FragmentFour;
import com.example.bbw.openzz.fragments.FragmentOne;
import com.example.bbw.openzz.fragments.FragmentThree;
import com.example.bbw.openzz.fragments.FragmentTwo;

/**
 * Created by bbw on 2017/10/23.
 * @author bibingwei
 */

public class TabRes {

    /**
     * 底部导航栏所有项
     */
    public static String[] getTabText(){
        String[] tabText = {"日报","图片","白日梦","果壳"};
        return tabText;
    }

    /**
     * 获得所有的碎片
     */
    public static Class[] getFragment(){
        Class[] getFragments = {FragmentOne.class, FragmentTwo.class, FragmentThree.class, FragmentFour.class};
        return getFragments;
    }
}
