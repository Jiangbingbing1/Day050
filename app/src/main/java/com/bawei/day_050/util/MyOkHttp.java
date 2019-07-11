package com.bawei.day_050.util;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class MyOkHttp {

    private OkHttpClient okHttpClient;
    private static final MyOkHttp ourInstance = new MyOkHttp();

    public static MyOkHttp getInstance() {
        return ourInstance;
    }

    private MyOkHttp() {
        HttpLoggingInterceptor loggingInterceptor=new HttpLoggingInterceptor();
        loggingInterceptor=loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient=new OkHttpClient.Builder()
                .addNetworkInterceptor(loggingInterceptor)
                .build();
    }

    public interface MyCallback{
        void success(String json);
        void error(String error);
    }
    private MyCallback myCallback;

    public void LastRequest(String url, final MyCallback myCallback){
         this.myCallback=myCallback;

        Request request=new Request.Builder().url(url).build();
         okHttpClient.newCall(request).enqueue(new Callback() {
             @Override
             public void onFailure(Call call, IOException e) {
                   myCallback.error(e.getMessage());
             }

             @Override
             public void onResponse(Call call, Response response) throws IOException {
                 myCallback.success(response.body().string());
             }
         });
    }

}
