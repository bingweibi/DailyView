package com.example.bbw.openzz.Gson.ZhiHuDailyBefore;

import com.example.bbw.openzz.Gson.ZhiHuDailyLatest.ZhiHuDailyLatest;

import java.util.List;

/**
 * Created by bbw on 2017/10/27.
 * @author bbw
 */

public class ZhiHuDailyBefore {

    private String date;
    private List<ZhiHuDailyLatest.Stories> storiesList;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<ZhiHuDailyLatest.Stories> getStoriesList() {
        return storiesList;
    }

    public void setStoriesList(List<ZhiHuDailyLatest.Stories> storiesList) {
        this.storiesList = storiesList;
    }
}
