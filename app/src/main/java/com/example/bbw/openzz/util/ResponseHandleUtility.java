package com.example.bbw.openzz.util;

import android.text.TextUtils;

import com.example.bbw.openzz.Gson.ZhiHuDailyBefore.ZhiHuDailyBefore;
import com.example.bbw.openzz.Gson.ZhiHuDailyFreeTalk.ZhiHuDailyFreeTalk;
import com.example.bbw.openzz.Gson.ZhiHuDailyHot.ZhiHuDailyHot;
import com.example.bbw.openzz.Gson.ZhiHuDailyLatest.ZhiHuDailyLatest;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by bbw on 2017/10/27.
 * @author bbw
 */

public class ResponseHandleUtility {

    public static List<ZhiHuDailyLatest.Stories> handleZhuHuDailyLatest(String responseText){

        ZhiHuDailyLatest zhiHuDailyLatest = new Gson().fromJson(responseText,ZhiHuDailyLatest.class);
        List<ZhiHuDailyLatest.Stories> storiesList = zhiHuDailyLatest.getStoriesList();
        return storiesList;
    }

    public static List<ZhiHuDailyLatest.Stories> handleZhuHuDailyBefore(String responseText){

        ZhiHuDailyBefore zhiHuDailyBefore = new Gson().fromJson(responseText,ZhiHuDailyBefore.class);
        List<ZhiHuDailyLatest.Stories> storiesList = zhiHuDailyBefore.getStoriesList();
        return storiesList;
    }

    public static List<ZhiHuDailyHot.Recent> handleZhiHuDailyHot(String responseText){

        ZhiHuDailyHot zhiHuDailyHot = new Gson().fromJson(responseText,ZhiHuDailyHot.class);
        List<ZhiHuDailyHot.Recent> recentList = zhiHuDailyHot.getRecentList();
        return recentList;
    }

    public static List<ZhiHuDailyLatest.Stories> handleZhuHuDailyFreeTalk(String responseText){

        ZhiHuDailyFreeTalk zhiHuDailyFreeTalk = new Gson().fromJson(responseText,ZhiHuDailyFreeTalk.class);
        List<ZhiHuDailyLatest.Stories> storiesList = zhiHuDailyFreeTalk.getStoriesList();
        return storiesList;
    }
}
