package com.example.bbw.openzz.util;

import android.text.TextUtils;
import android.util.Log;

import com.example.bbw.openzz.Model.ZhiHuDailyBefore.ZhiHuDailyBefore;
import com.example.bbw.openzz.Model.ZhiHuDailyFreeTalk.ZhiHuDailyFreeTalk;
import com.example.bbw.openzz.Model.ZhiHuDailyHot.Recent;
import com.example.bbw.openzz.Model.ZhiHuDailyHot.ZhiHuDailyHot;
import com.example.bbw.openzz.Model.ZhiHuDailyLatest.StoryBean;
import com.example.bbw.openzz.Model.ZhiHuDailyLatest.ZhiHuDailyLatest;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bbw on 2017/10/27.
 * @author bbw
 */

public class ResponseHandleUtility {

    public static StoryBean handleZhuHuDailyLatest(String responseText) throws JSONException {

        if (!TextUtils.isEmpty(responseText)){
            JSONObject jsonObject = new JSONObject(responseText);
            JSONArray jsonArray = jsonObject.getJSONArray("stories");
            String content = jsonArray.getJSONObject(0).toString();
            return new Gson().fromJson(content,StoryBean.class);
        }
        return null;
    }

    public static ArrayList<StoryBean> handleZhuHuDailyBefore(String responseText){

        ZhiHuDailyBefore zhiHuDailyBefore = new Gson().fromJson(responseText,ZhiHuDailyBefore.class);
        ArrayList<StoryBean> storiesList = zhiHuDailyBefore.getStoriesList();
        return storiesList;
    }

    public static ArrayList<Recent> handleZhiHuDailyHot(String responseText){

        ZhiHuDailyHot zhiHuDailyHot = new Gson().fromJson(responseText,ZhiHuDailyHot.class);
        ArrayList<Recent> recentList = zhiHuDailyHot.getRecentList();
        return recentList;
    }

    public static ArrayList<StoryBean> handleZhuHuDailyFreeTalk(String responseText){

        ZhiHuDailyFreeTalk zhiHuDailyFreeTalk = new Gson().fromJson(responseText,ZhiHuDailyFreeTalk.class);
        ArrayList<StoryBean> storiesList = zhiHuDailyFreeTalk.getStoriesList();
        return storiesList;
    }
}
