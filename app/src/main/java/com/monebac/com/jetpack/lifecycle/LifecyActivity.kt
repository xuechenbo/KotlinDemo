package com.monebac.com.jetpack.lifecycle

import androidx.lifecycle.LifecycleOwner
import com.monebac.com.R
import com.monebac.com.base.BaseActivity


class LifecyActivity : BaseActivity(), LifecycleOwner {

    override fun initLayout(): Int {
        return R.layout.act_lifecy
    }

    override fun initData() {
        //??再改
        lifecycle.addObserver(object : TestLifeCycle() {})
    }

}
