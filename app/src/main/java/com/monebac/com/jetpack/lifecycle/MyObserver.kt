package com.monebac.com.jetpack.lifecycle

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.monebac.com.utils.LogsUtils

class MyObserver : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun connectResumeListener() {
        LogsUtils.d("MyObserver==ON_RESUME")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun connetPauseListener() {
        LogsUtils.d("MyObserver==ON_PAUSE")
    }


    //在任何生命周期是触发
    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    fun onAny() {

    }
}