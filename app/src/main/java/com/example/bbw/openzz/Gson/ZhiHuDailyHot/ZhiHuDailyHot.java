package com.example.bbw.openzz.Gson.ZhiHuDailyHot;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by bbw on 2017/10/26.
 * @author bbw
 */

public class ZhiHuDailyHot {

    public List<Recent> recentList;

    public List<Recent> getRecentList() {
        return recentList;
    }

    public void setRecentList(List<Recent> recentList) {
        this.recentList = recentList;
    }

    public class Recent {
        @SerializedName("news_id")
        private int id;
        @SerializedName("url")
        private String contentUrl;
        @SerializedName("thumbnail")
        private String imageUrl;
        private String title;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getContentUrl() {
            return contentUrl;
        }

        public void setContentUrl(String contentUrl) {
            this.contentUrl = contentUrl;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
