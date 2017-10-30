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

    public class StoryBean {

        private String title;
        private List<String> images;
        private int id;
        private int type;
        private String ga_prefix;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
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
