package com.example.bbw.openzz.Gson.ZhiHuDailyLatest;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by bbw on 2017/10/26.
 * @author bbw
 */

public class ZhiHuDailyLatest {

    private List<Stories> storiesList;

    public List<Stories> getStoriesList() {
        return storiesList;
    }

    public void setStoriesList(List<Stories> storiesList) {
        this.storiesList = storiesList;
    }

    public class Stories {
        private String title;
        private int id;
        private List<String> iamgesList;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public List<String> getIamgesList() {
            return iamgesList;
        }

        public void setIamgesList(List<String> iamgesList) {
            this.iamgesList = iamgesList;
        }
    }
}
