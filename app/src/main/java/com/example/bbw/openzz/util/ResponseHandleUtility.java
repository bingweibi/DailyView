package com.example.bbw.openzz.util;

import com.example.bbw.openzz.Model.ZhiHuDailyLatest.ZhiHuDailyLatest;
import com.google.gson.Gson;

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
                JSONObject newsInJson = jsonArray.getJSONObject(i);
                int id = newsInJson.optInt("id");
                String title = newsInJson.optString("title");
                String image = "";
                if (newsInJson.has("images")) {
                    image = (String) newsInJson.getJSONArray("images").get(0);

                }
                ZhiHuDailyLatest.StoryBean news = new ZhiHuDailyLatest.StoryBean(title,image,id);
                storyBeansList.add(news);
            }
        return storyBeansList;
    }

}
