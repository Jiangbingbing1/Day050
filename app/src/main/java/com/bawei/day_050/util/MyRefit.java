package com.bawei.day_050.util;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface MyRefit {

    @GET("commodity/v1/commodityList")
    Call<ResponseBody> getInfo(@QueryMap Map<String,String> map);



}
