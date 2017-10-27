package com.example.bbw.openzz.fragmentTab;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bbw on 2017/10/25.
 * @author bibingwei
 */

public class FragmentTwoTab {

    private static final List<FragmentTab> selected = new ArrayList<>();

    static {
        selected.add(new FragmentTab("推荐"));
        selected.add(new FragmentTab("最热"));
        selected.add(new FragmentTab("最新"));
        selected.add(new FragmentTab("精选"));
    }

    /**
     * 获得头部tab的所有项
     */
    public static List<FragmentTab> getSelected(){
        return selected;
    }
}
