package com.example.bbw.openzz.Gson.ZhiHuDailyLatest;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by bbw on 2017/10/26.
 * @author bbw
 */

public class ZhiHuDailyLatest {

    private List<Stories> storiesList;

    @SerializedName("top_stories")
    private List<TopStories> topStoriesList;

    public List<Stories> getStoriesList() {
        return storiesList;
    }

    public void setStoriesList(List<Stories> storiesList) {
        this.storiesList = storiesList;
    }

    public List<TopStories> getTopStoriesList() {
        return topStoriesList;
    }

    public void setTopStoriesList(List<TopStories> topStoriesList) {
        this.topStoriesList = topStoriesList;
    }
}
