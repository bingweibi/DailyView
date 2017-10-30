package com.example.bbw.openzz.Model.ZhiHuDailyLatest;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by bbw on 2017/10/26.
 * @author bbw
 */

public class ZhiHuDailyLatest {

    private String date;

    private ArrayList<StoryBean> storiesList;

    private ArrayList<TopStory> topStories;

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

    public ArrayList<TopStory> getTopStories() {
        return topStories;
    }

    public void setTopStories(ArrayList<TopStory> topStories) {
        this.topStories = topStories;
    }
}
