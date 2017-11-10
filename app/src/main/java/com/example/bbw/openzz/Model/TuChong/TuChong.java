package com.example.bbw.openzz.Model.TuChong;

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

public class TuChong {

    private List<PostList> postLists;

    public List<PostList> getPostLists() {
        return postLists;
    }

    public void setPostLists(List<PostList> postLists) {
        this.postLists = postLists;
    }

    public class PostList {

        private String url;
        private String excerpt;
        private List<Images> imagesList;
        private List<Site> siteList;

        public String getExcerpt() {
            return excerpt;
        }

        public void setExcerpt(String excerpt) {
            this.excerpt = excerpt;
        }

        public List<Site> getSiteList() {
            return siteList;
        }

        public void setSiteList(List<Site> siteList) {
            this.siteList = siteList;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public List<Images> getImagesList() {
            return imagesList;
        }

        public void setImagesList(List<Images> imagesList) {
            this.imagesList = imagesList;
        }

        public class Images{
            private int img_id;

            public int getImg_id() {
                return img_id;
            }

            public void setImg_id(int img_id) {
                this.img_id = img_id;
            }
        }

        public class Site{
            private String name;
            private String icon;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }
        }
    }
}
