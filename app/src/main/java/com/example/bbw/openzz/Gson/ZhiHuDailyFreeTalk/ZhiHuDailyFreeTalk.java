package com.example.bbw.openzz.Gson.ZhiHuDailyFreeTalk;

import com.example.bbw.openzz.Gson.ZhiHuDailyLatest.Stories;

import java.util.List;

/**
 * Created by bbw on 2017/10/27.
 * @author bbw
 */

public class ZhiHuDailyFreeTalk {

    private String timestamp;
    private String name;
    private List<Stories> storiesList;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Stories> getStoriesList() {
        return storiesList;
    }

    public void setStoriesList(List<Stories> storiesList) {
        this.storiesList = storiesList;
    }
}
