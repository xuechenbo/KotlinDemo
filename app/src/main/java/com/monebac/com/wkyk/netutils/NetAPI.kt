package com.monebac.com.wkyk.netutils

import io.reactivex.Observable
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface NetAPI {
    @POST("request.app")
    @FormUrlEncoded
    fun getRequeData(@FieldMap body: Map<String, String>): Observable<BaseResp>
}
