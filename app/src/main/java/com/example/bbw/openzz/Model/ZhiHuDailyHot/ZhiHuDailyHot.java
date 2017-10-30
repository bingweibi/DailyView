package com.example.bbw.openzz.Model.ZhiHuDailyHot;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bbw on 2017/10/26.
 * @author bbw
 */

public class ZhiHuDailyHot {

    public ArrayList<Recent> recentList;

    public ArrayList<Recent> getRecentList() {
        return recentList;
    }

    public void setRecentList(ArrayList<Recent> recentList) {
        this.recentList = recentList;
    }
}
