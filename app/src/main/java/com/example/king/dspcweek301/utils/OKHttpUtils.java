package com.example.king.dspcweek301.utils;

import android.os.Handler;
import android.text.TextUtils;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OKHttpUtils {

    private static OKHttpUtils instance;
    private final Handler handler;
    private final OkHttpClient okHttpClient;

    private OKHttpUtils() {
        handler = new Handler();
        okHttpClient = new OkHttpClient.Builder()
                .readTimeout(5,TimeUnit.SECONDS)
                .connectTimeout(5,TimeUnit.SECONDS)
                .writeTimeout(5,TimeUnit.SECONDS)
                .build();
    }

    public static OKHttpUtils getInstance(){
        if (instance == null) {
            synchronized (OKHttpUtils.class){
                if (instance == null) {
                    instance = new OKHttpUtils();
                }
            }
        }
        return instance;
    }

    /**
     * GET解析
     * @param api
     * @param param
     * @param okHttpUtilsCallBack
     */
    public void toGet(String api, Map<String,String> param, final OKHttpUtilsCallBack okHttpUtilsCallBack){
        StringBuilder s = new StringBuilder();
        if (param != null) {
            for (Map.Entry<String, String> p : param.entrySet()) {
                s.append(p.getKey()).append("=").append(p.getValue()).append("&");
            }
        }

        Request request = new Request.Builder()
                .get()
                .url(api+"?"+s.toString())
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        okHttpUtilsCallBack.onFile("网络错误!");
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String res = response.body().string();
                if (!TextUtils.isEmpty(res)){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            okHttpUtilsCallBack.onSuccess(res);
                        }
                    });
                }
            }
        });
    }

    /**
     * POST解析
     * @param api
     * @param param
     * @param okHttpUtilsCallBack
     */
    public void toPost(String api, Map<String,String> param, final OKHttpUtilsCallBack okHttpUtilsCallBack){
        FormBody.Builder  builder = new FormBody.Builder();
        if (param != null) {
            for (Map.Entry<String, String> p : param.entrySet()) {
                builder.add(p.getKey(),p.getValue());
            }
        }

        Request request = new Request.Builder()
                .post(builder.build())
                .url(api)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        okHttpUtilsCallBack.onFile("网络错误!");
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String res = response.body().string();
                if (!TextUtils.isEmpty(res)){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            okHttpUtilsCallBack.onSuccess(res);
                        }
                    });
                }
            }
        });
    }

}
