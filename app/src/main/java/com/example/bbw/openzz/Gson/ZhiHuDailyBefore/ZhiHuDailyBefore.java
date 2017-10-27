package com.example.bbw.openzz.Gson.ZhiHuDailyBefore;

import com.example.bbw.openzz.Gson.ZhiHuDailyLatest.Stories;

import java.util.List;

/**
 * Created by bbw on 2017/10/27.
 * @author bbw
 */

public class ZhiHuDailyBefore {

    private String date;
    private List<Stories> storiesList;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Stories> getStoriesList() {
        return storiesList;
    }

    public void setStoriesList(List<Stories> storiesList) {
        this.storiesList = storiesList;
    }
}
