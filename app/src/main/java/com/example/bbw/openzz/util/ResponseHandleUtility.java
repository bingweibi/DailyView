package com.example.bbw.openzz.util;

import com.example.bbw.openzz.Model.Gank.Gank;
import com.example.bbw.openzz.Model.ZhiHuDaily.StoryComments;
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

    public static List<StoryComments.CommentBean> handleStoryComments(String responseText)throws JSONException{

        JSONObject jsonObject = new JSONObject(responseText);
        JSONArray jsonArray = jsonObject.getJSONArray("comments");
        List<StoryComments.CommentBean> storyCommentsList = new ArrayList<>();
        for (int i=0;i<jsonArray.length();i++){
            JSONObject commentsInJson = jsonArray.getJSONObject(i);
            String author = commentsInJson.optString("author");
            String content = commentsInJson.optString("content");
            String avatar = commentsInJson.optString("avatar");
            StoryComments.CommentBean comments = new StoryComments.CommentBean(author,content,avatar);
            storyCommentsList.add(comments);
        }
        return storyCommentsList;
    }

    public static List<Gank.results> handleGank(String responseText) throws JSONException{

        JSONObject jsonObject = new JSONObject(responseText);
        JSONArray jsonArray = jsonObject.getJSONArray("results");
        List<Gank.results> results = new ArrayList<>();
        for (int i=0;i<jsonArray.length();i++){
            JSONObject picInJson = jsonArray.getJSONObject(i);
            String url = picInJson.optString("url");
            String desc = picInJson.optString("desc");
            Gank.results picMessage = new Gank.results(url,desc);
            results.add(picMessage);
        }
        return results;
    }
}
