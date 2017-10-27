package com.example.bbw.openzz.Gson.ZhiHuDailyHot;

import com.google.gson.annotations.SerializedName;

/**
 * Created by bbw on 2017/10/27.
 * @author bbw
 */

public class Recent {

    @SerializedName("news_id")
    private int id;
    @SerializedName("url")
    private String contentUrl;
    @SerializedName("thumbnail")
    private String imageUrl;
    private String title;

}
