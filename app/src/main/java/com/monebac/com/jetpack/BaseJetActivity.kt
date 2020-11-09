package com.monebac.com.jetpack

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.FragmentActivity

abstract class BaseJetActivity : FragmentActivity() {
    lateinit var context: Activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this
        setContentView(initLayout())
        //设置数据
        initData()
    }


    abstract fun initLayout(): Int

    abstract fun initData()

}
