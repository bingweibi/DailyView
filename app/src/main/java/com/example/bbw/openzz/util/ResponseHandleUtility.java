package com.example.bbw.openzz.util;

import com.example.bbw.openzz.Model.ZhiHuDaily.StoryDetail;
import com.example.bbw.openzz.Model.ZhiHuDaily.ZhiHuDaily;
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

    public static List<ZhiHuDaily.StoryBean> handleZhuHuDailyLatest(String responseText) throws JSONException {

            JSONObject jsonObject = new JSONObject(responseText);
            JSONArray jsonArray = jsonObject.getJSONArray("stories");
            List<ZhiHuDaily.StoryBean> storyBeansList = new ArrayList<>();
            for (int i=0;i<jsonArray.length();i++){
                JSONObject newsInJson = jsonArray.getJSONObject(i);
                int id = newsInJson.optInt("id");
                String title = newsInJson.optString("title");
                String image = "";
                if (newsInJson.has("images")) {
                    image = (String) newsInJson.getJSONArray("images").get(0);

                }
                ZhiHuDaily.StoryBean news = new ZhiHuDaily.StoryBean(title,image,id);
                storyBeansList.add(news);
            }
        return storyBeansList;
    }

    public static StoryDetail handleStoryDetail(String responseText) throws JSONException {

        Gson gson = new Gson();
        StoryDetail storyDetail = gson.fromJson(responseText,StoryDetail.class);
        return storyDetail;
    }
}
