package com.example.bbw.openzz.Model.ZhiHuDailyLatest;


import java.util.List;

/**
 * Created by bbw on 2017/10/26.
 * @author bbw
 */

public class ZhiHuDailyLatest {

    private List<StoryBean> storiesList;

    public List<StoryBean> getStoriesList() {
        return storiesList;
    }

    public void setStoriesList(List<StoryBean> storiesList) {
        this.storiesList = storiesList;
    }

    public static class StoryBean {

        private String title;
        private String images;
        private int id;

        public StoryBean(String title, String images, int id) {
            this.title = title;
            this.images = images;
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
