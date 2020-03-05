package com.example.retrofittest;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Url;

/**
 * @author: Administrator
 * @date: 2020-03-03
 */
public interface IpService {
    @Headers({
            "Accept-Encoding:application/json",
            "User-Agent:MoonRetrofit"
    })
    @GET("getIpInfo.php?ip=59.108.54.37")
    Call<ResponseBody> getIpMsgGet();
    /**
     * 采用指定的全路径访问
     * @param url   网址的全路径
     * @return
     */
    @Headers({
            "User-Agent: MoonRetrofit"
    })
    @GET
    Call<ResponseBody> getIpMsgGet1(@Url String url);

}
