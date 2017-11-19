package com.example.bbw.openzz.Model.Gank;

import java.util.List;

/**
 * Created by bbw on 2017/11/10.
 * @author bbw
 *
 * url:图片的url
 * excerpt:图片的描述
 * img_id:图片的ID，用于查看图片的额外信息
 * name:图片作者的名字
 * icon：图片作者的头像
 */

public class Gank {

    private List<results> results;

    public List<results> getResults() {
        return results;
    }

    public void setResults(List<results> results) {
        this.results = results;
    }

    public static class results {

        private String desc;

        private String url;

        private String who;

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public results(String url,String desc,String who) {
            this.url = url;
            this.desc = desc;
            this.who = who;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
