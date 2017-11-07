package com.example.bbw.openzz.db;

import org.litepal.crud.DataSupport;

/**
 * Created by bbw on 2017/10/26.
 * @author bibingwei
 */

public class ZhiHuDailyBean extends DataSupport{

    private int id;
    private String imageUrl;
    private String title;

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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
