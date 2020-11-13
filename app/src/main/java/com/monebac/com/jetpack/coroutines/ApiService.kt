package com.monebac.com.jetpack.coroutines

import com.monebac.com.wkyk.netutils.BaseResp
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("request.app")
    suspend fun getImage(@FieldMap body: Map<String, String>): BaseResp

}