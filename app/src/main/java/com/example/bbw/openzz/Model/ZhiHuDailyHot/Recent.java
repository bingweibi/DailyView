package com.example.bbw.openzz.Model.ZhiHuDailyHot;

import com.google.gson.annotations.SerializedName;

/**
 * Created by bbw on 2017/10/29.
 * @author bbw
 */

public class Recent {

    @SerializedName("news_id")
    private int id;
    private String url;
    private String thumbnail;
    private String title;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
