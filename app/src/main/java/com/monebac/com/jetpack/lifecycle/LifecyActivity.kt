package com.monebac.com.jetpack.lifecycle

import androidx.lifecycle.LifecycleOwner
import com.monebac.com.R
import com.monebac.com.base.BaseActivity
import com.monebac.com.utils.LogsUtils
import kotlinx.android.synthetic.main.layout_title.*


class LifecyActivity : BaseActivity(), LifecycleOwner {

    /**
     * 生命周期
     * LifecycleOwner一个接口类，getLifecycle()
     */

    override fun initLayout(): Int {
        return R.layout.act_lifecy
    }

    override fun initData() {
        tv_title.text = "lifecycle"
        back.setOnClickListener { finish() }

        LogsUtils.d(lifecycle.currentState.name)
        lifecycle.addObserver(MyObserver())
        LogsUtils.d(lifecycle.currentState.name)
    }


    override fun onResume() {
        super.onResume()
        LogsUtils.d("Activity------onResume  ${lifecycle.currentState.name}")
        LogsUtils.d("Activity------onResume")
    }

    override fun onPause() {
        super.onPause()
        LogsUtils.d("Activity------onPause  ${lifecycle.currentState.name}")
        LogsUtils.d("Activity------onPause")
    }

}
