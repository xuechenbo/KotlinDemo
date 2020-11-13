package com.monebac.com.jetpack.coroutines

import okhttp3.OkHttpClient.Builder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

object NetworkService {
    //实例Retrofit

    //retorfit实例，在这里做一些统一网络配置，如添加转换器、设置超时时间等
    private val retrofit = Retrofit.Builder()
            .client(Builder().callTimeout(5, TimeUnit.SECONDS).build())
            .baseUrl("http://chengxinshenghuo.llyzf.cn:80/lly-posp-proxy/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val apiService = retrofit.create<ApiService>()

}