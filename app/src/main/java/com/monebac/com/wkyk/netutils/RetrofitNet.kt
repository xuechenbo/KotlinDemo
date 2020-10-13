package com.monebac.com.wkyk.netutils

import com.monebac.com.BuildConfig
import com.monebac.com.utils.LogsUtils
import com.monebac.com.wkyk.Constant
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitNet {
    private val build: Retrofit

    val api: NetAPI
        get() = build.create(NetAPI::class.java)

    init {
        val builder = OkHttpClient.Builder()
        builder.connectTimeout(DEFAULT_TIME_OUT.toLong(), TimeUnit.SECONDS)//连接超时时间
        builder.readTimeout(DEFAULT_READ_TIME_OUT.toLong(), TimeUnit.SECONDS)//读操作超时时间
        if (BuildConfig.DEBUG) {//发布版本不再打印  debugb版本打印   日志拦截器
            // 日志显示级别
            val level = HttpLoggingInterceptor.Level.BODY
            //新建log拦截器
            val loggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message -> LogsUtils.e("OkHttp====Message:$message") })
            loggingInterceptor.level = level
            //OkHttp进行添加拦截器loggingInterceptor
            builder.addInterceptor(loggingInterceptor)
        }

        //创建Retrofit
        build = Retrofit.Builder().client(builder.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_MAIN_URL)
                .build()

    }

    companion object {
        val BASE_MAIN_URL = Constant.RETROFIT_URL
        private const val DEFAULT_TIME_OUT = 5  //超时时间5秒
        private const val DEFAULT_READ_TIME_OUT = 10
        private var retrofitServiceManager: RetrofitNet? = null

        val instance: RetrofitNet?
            get() {
                if (retrofitServiceManager == null) {
                    synchronized(RetrofitNet::class.java) {
                        if (retrofitServiceManager == null) {
                            retrofitServiceManager = RetrofitNet()
                        }
                    }
                }
                return retrofitServiceManager
            }
    }
}
