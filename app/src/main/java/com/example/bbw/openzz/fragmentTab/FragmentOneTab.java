package com.example.bbw.openzz.fragmentTab;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bbw on 2017/10/23.
 * @author bibingwei
 */

public class FragmentOneTab {

    private static final List<FragmentTab> selected = new ArrayList<>();

    static {
        selected.add(new FragmentTab("推荐"));
        selected.add(new FragmentTab("热门"));
        selected.add(new FragmentTab("吐槽"));
        selected.add(new FragmentTab("历史"));
    }

    /**
     * 获得头部tab的所有项
     */
    public static List<FragmentTab> getSelected(){
        return selected;
    }
}
