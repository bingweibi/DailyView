package com.example.bbw.openzz.Model.ZhiHuDailyBefore;

import com.example.bbw.openzz.Model.ZhiHuDailyLatest.StoryBean;
import com.example.bbw.openzz.Model.ZhiHuDailyLatest.ZhiHuDailyLatest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bbw on 2017/10/27.
 * @author bbw
 */

public class ZhiHuDailyBefore {

    private String date;
    private ArrayList<StoryBean> storiesList;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<StoryBean> getStoriesList() {
        return storiesList;
    }

    public void setStoriesList(ArrayList<StoryBean> storiesList) {
        this.storiesList = storiesList;
    }
}
