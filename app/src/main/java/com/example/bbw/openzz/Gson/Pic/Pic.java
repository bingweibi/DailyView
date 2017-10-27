package com.example.bbw.openzz.Gson.Pic;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by bbw on 2017/10/27.
 * @author bbw
 */

public class Pic {

    private String result;
    @SerializedName("post_list")
    private List<PostList> postlist;
}
