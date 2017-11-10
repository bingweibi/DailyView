package com.example.bbw.openzz.util;

import com.example.bbw.openzz.Model.TuChong.TuChong;
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

    public static List<TuChong.PostList> handleTuChongPic(String responseText) throws JSONException{

        JSONObject jsonObject = new JSONObject(responseText);
        JSONArray jsonArray = jsonObject.getJSONArray("post_list");
        List<TuChong.PostList> postLists = new ArrayList<>();
        for (int i=0;i<jsonArray.length();i++){
            JSONObject picInJson = jsonArray.getJSONObject(i);
            String url = picInJson.optString("url");
            String excerpt = picInJson.optString("excerpt");

            JSONArray imagesJSONArray = picInJson.optJSONArray("images");
            List<TuChong.PostList.Images> imagesIDList = new ArrayList<>();
            for (int j=0;j<imagesJSONArray.length();j++){
                JSONObject imageInJson = imagesJSONArray.getJSONObject(j);
                int img_id = imageInJson.optInt("img_id");
                TuChong.PostList.Images imagesID = new TuChong.PostList.Images(img_id);
                imagesIDList.add(imagesID);
            }

            JSONObject authorJSONObject = picInJson.optJSONObject("site");
            List<TuChong.PostList.Site> imagesAuthorList = new ArrayList<>();
            String name = authorJSONObject.getString("name");
            String icon = authorJSONObject.getString("icon");
            TuChong.PostList.Site authorMessage = new TuChong.PostList.Site(name,icon);
            imagesAuthorList.add(authorMessage);
            TuChong.PostList picMessage = new TuChong.PostList(url,excerpt,imagesIDList,imagesAuthorList);
            postLists.add(picMessage);
        }
        return postLists;
    }
}
