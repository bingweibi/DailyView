package com.example.bbw.openzz.Model.GuokrScience;

import com.google.gson.annotations.SerializedName;

/**
 * Created by bbw on 2017/10/27.
 * @author bbw
 */

public class Result {

    @SerializedName("is_replayable")
    private boolean replayable;
    private int id;
    private String summary;
    @SerializedName("small_image")
    private String imageUrl;
    @SerializedName("resource_url")
    private String contentUrl;
}
