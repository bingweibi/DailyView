package com.example.bbw.openzz.Gson.ZhiHuDailyLatest;

import java.util.List;

/**
 * Created by bbw on 2017/10/27.
 * @author bbw
 */

public class Stories {

    /**
     * images : ["http://pic4.zhimg.com/5615a788a32c2cc9469f825c4622879b.jpg"]
     * id : 9115087
     * title : "给宝宝玩的宇宙启蒙，「摘下」浩瀚星空只需三步"
     * date:"20171027"
     */

    private int id;
    private String title;
    private String date;

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    private List<String> images;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
