package com.monebac.com

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import kotlin.properties.Delegates

class App : Application() {

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }


    //TODO Delegates 保证自身为空的工具？？
    companion object {
        private var instance: App by Delegates.notNull()
        fun instance() = instance
    }


}