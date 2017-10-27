package com.example.bbw.openzz.Gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by bbw on 2017/10/26.
 * @author bbw
 */

public class Recent {

    @SerializedName("news_id")
    public int id;
    @SerializedName("url")
    public String contentUrl;
    public String title;
    @SerializedName("thumbnail")
    public String imageUrl;
}
