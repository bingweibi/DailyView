package com.example.bbw.openzz.fragmentTab;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bbw on 2017/10/25.
 */

public class FragmentFourTab {

    private static final List<FragmentTab> selected = new ArrayList<>();

    static {
        selected.add(new FragmentTab("热门"));
        selected.add(new FragmentTab("专访"));
        selected.add(new FragmentTab("速读"));
        selected.add(new FragmentTab("真相"));
    }

    /**
     * 获得头部tab的所有项
     */
    public static List<FragmentTab> getSelected(){
        return selected;
    }
}
