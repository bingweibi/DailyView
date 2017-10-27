package com.example.bbw.openzz.util;

import android.text.TextUtils;

import com.example.bbw.openzz.Gson.ZhiHuDailyLatest.Stories;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by bbw on 2017/10/27.
 * @author bbw
 */

public class ResponseHandleUtility {

    public static Stories handleZhiHuDailyResponse(String response) throws JSONException {

        if(!TextUtils.isEmpty(response)){
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("stories");
            String dailyContentStories = jsonArray.getJSONObject(0).toString();
            return new Gson().fromJson(dailyContentStories,Stories.class);
        }
        return null;
    }
}
