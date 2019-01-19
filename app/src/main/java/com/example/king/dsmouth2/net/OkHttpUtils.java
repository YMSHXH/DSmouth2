package com.example.king.dsmouth2.net;

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
import okhttp3.logging.HttpLoggingInterceptor;

public class OkHttpUtils {
    private static OkHttpUtils instance;
    private final Handler handler;
    private final OkHttpClient okHttpClient;

    private OkHttpUtils() {
        handler = new Handler();
        //日志拦截器
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .readTimeout(5,TimeUnit.SECONDS)
                .writeTimeout(5,TimeUnit.SECONDS)
                .connectTimeout(5,TimeUnit.SECONDS)
                .build();
    }

    public static OkHttpUtils getInstance(){
        if (instance == null) {
            synchronized (OkHttpUtils.class){
                if (instance == null) {
                    instance = new OkHttpUtils();
                }
            }
        }
        return instance;
    }

    /**
     * get 请求
     * @param apiurl
     * @param params
     * @param okHttpUtilsCallBack
     */
    public void toGet(String apiurl, Map<String,String> params, final OkHttpUtilsCallBack okHttpUtilsCallBack){
        StringBuilder stringBuilder = new StringBuilder();
        if (params != null && params.size() >0) {
            for (Map.Entry<String, String> p : params.entrySet()) {
                stringBuilder.append(p.getKey()).append("=").append(p.getValue()).append("&");
            }
        }

        Request request = new Request.Builder()
                .get()
                .url(apiurl + "?" + stringBuilder.toString())
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
                final String result = response.body().string();
                if (!TextUtils.isEmpty(result)) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            okHttpUtilsCallBack.onSuccess(result);
                        }
                    });
                }
            }
        });
    }

    /**
     * post 请求
     * @param apiurl
     * @param params
     * @param okHttpUtilsCallBack
     */
    public void toPost(String apiurl, Map<String,String> params, final OkHttpUtilsCallBack okHttpUtilsCallBack){
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, String> p : params.entrySet()) {
            builder.add(p.getKey(),p.getValue());
        }


        Request request = new Request.Builder()
                .url(apiurl)
                .post(builder.build())
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
                final String result = response.body().string();
                if (!TextUtils.isEmpty(result)) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            okHttpUtilsCallBack.onSuccess(result);
                        }
                    });
                }
            }
        });
    }


    /**
     * 取消所有请求，好处：节省开销：内存开销，cpu的开销（线程开销）
     */
    public void cancelAllTask(){
        if (okHttpClient != null) {
            okHttpClient.dispatcher().cancelAll();
        }
    }
}
