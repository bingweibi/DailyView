package com.example.bbw.openzz.Model.ZhiHuDailyFreeTalk;


import com.example.bbw.openzz.Model.ZhiHuDailyLatest.ZhiHuDailyLatest;

import java.util.List;

/**
 * Created by bbw on 2017/10/27.
 * @author bbw
 */

public class ZhiHuDailyFreeTalk {

    private List<ZhiHuDailyLatest.StoryBean> storiesList;

    public List<ZhiHuDailyLatest.StoryBean> getStoriesList() {
        return storiesList;
    }

    public void setStoriesList(List<ZhiHuDailyLatest.StoryBean> storiesList) {
        this.storiesList = storiesList;
    }
}
