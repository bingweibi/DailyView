package com.example.bbw.openzz.Gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by bbw on 2017/10/26.
 * @author bbw
 */

public class ZhiHuDailyLatest {

    public List<Stories> storiesList;

    @SerializedName("top_stories")
    public List<TopStories> topStoriesList;
}
