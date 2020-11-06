package com.monebac.com.jetpack.viewmodel

import androidx.lifecycle.ViewModelProviders
import com.monebac.com.R
import com.monebac.com.base.BaseActivity
import com.monebac.com.utils.LogsUtils
import kotlinx.android.synthetic.main.layout_title.*

class ViewModelActivity : BaseActivity() {
    /**
     * 可以解决数据不一保存问题
     * ViewModel
     * MVVM   中的VM
     * 将界面和数据分离
     */
    private var timerViewModel: TimerViewModel? = null

    override fun initLayout(): Int {
        return R.layout.act_view_model
    }

    override fun initData() {
        tv_title.text = "ViewModel"
        back.setOnClickListener { finish() }

        //实例化
        timerViewModel = ViewModelProviders.of(this).get(TimerViewModel::class.java)
        timerViewModel!!.setOnTimeChangeListener { second ->
            LogsUtils.e(second.toString() + "")
        }
        timerViewModel!!.startTiming()
    }


}
