package com.example.bbw.openzz.Gson.ZhiHuDailyLatest;

import com.google.gson.annotations.SerializedName;

/**
 * Created by bbw on 2017/10/27.
 * @author bbw
 */

public class TopStories {
    /**
     * images : "https://pic2.zhimg.com/v2-61a6df61efc86afbac22f1535eafc5f1.jpg"
     * type : 0
     * id : 9654090
     * ga_prefix : "102617"
     * title : "我们能吃到美味的饭菜，真要感谢历代厨师的努力"
     */

    private int id;
    private String title;
    @SerializedName("images")
    private String imageUrl;

    public int getId() {
        return id;
    }
}
