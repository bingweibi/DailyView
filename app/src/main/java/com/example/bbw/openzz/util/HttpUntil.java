package com.example.bbw.openzz.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by bbw on 2017/10/26.
 * @author bibingwei
 */

public class HttpUntil {

    public static void sendOkHttpRequest(String address,okhttp3.Callback callBack){
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        okHttpClient.newCall(request).enqueue(callBack);
    }
}
