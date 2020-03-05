package com.example.retrofittest;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * @author: Administrator
 * @date: 2020-03-03
 */
public interface RequestServices {
    @GET("basil2style")
    Call<ResponseBody> getString();
}
