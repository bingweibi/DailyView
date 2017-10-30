package com.example.bbw.openzz.util;

import android.text.TextUtils;

import com.example.bbw.openzz.Model.ZhiHuDailyBefore.ZhiHuDailyBefore;
import com.example.bbw.openzz.Model.ZhiHuDailyFreeTalk.ZhiHuDailyFreeTalk;
import com.example.bbw.openzz.Model.ZhiHuDailyHot.Recent;
import com.example.bbw.openzz.Model.ZhiHuDailyHot.ZhiHuDailyHot;
import com.example.bbw.openzz.Model.ZhiHuDailyLatest.ZhiHuDailyLatest;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bbw on 2017/10/27.
 * @author bbw
 */

public class ResponseHandleUtility {

    public static List<ZhiHuDailyLatest.StoryBean> handleZhuHuDailyLatest(String responseText) throws JSONException {

            JSONObject jsonObject = new JSONObject(responseText);
            JSONArray jsonArray = jsonObject.getJSONArray("stories");
            List<ZhiHuDailyLatest.StoryBean> storyBeansList = new ArrayList<>();
            for (int i=0;i<jsonArray.length();i++){
                String element = jsonArray.get(i).toString();
                ZhiHuDailyLatest.StoryBean storyBean = new Gson().fromJson(element,ZhiHuDailyLatest.StoryBean.class);
                storyBeansList.add(storyBean);
            }
        return storyBeansList;
    }

    public static ZhiHuDailyBefore handleZhuHuDailyBefore(String responseText) throws JSONException{

        if (!TextUtils.isEmpty(responseText)){
            JSONObject jsonObject = new JSONObject(responseText);
            JSONArray jsonArray = jsonObject.getJSONArray("stories");
            String content = jsonArray.getJSONObject(0).toString();
            return new Gson().fromJson(content,ZhiHuDailyBefore.class);
        }
        return null;
    }

    public static ArrayList<Recent> handleZhiHuDailyHot(String responseText){

        ZhiHuDailyHot zhiHuDailyHot = new Gson().fromJson(responseText,ZhiHuDailyHot.class);
        ArrayList<Recent> recentList = zhiHuDailyHot.getRecentList();
        return recentList;
    }

    public static ZhiHuDailyFreeTalk handleZhuHuDailyFreeTalk(String responseText) throws JSONException{

        if (!TextUtils.isEmpty(responseText)){
            JSONObject jsonObject = new JSONObject(responseText);
            JSONArray jsonArray = jsonObject.getJSONArray("stories");
            String content = jsonArray.getJSONObject(0).toString();
            return new Gson().fromJson(content,ZhiHuDailyFreeTalk.class);
        }
        return null;
    }
}
