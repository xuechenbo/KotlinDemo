package com.monebac.com.ktolingbaic.kotlinbasic.model

import com.monebac.com.utils.LogsUtils

class DogModel() : CalloutInterface {

    var listener: ClickListener? = null

    override var skilledSports: String = "接口属性:汪汪汪"

    override fun getB() {
        LogsUtils.e("看个门")
    }

    fun getCall() {
        LogsUtils.e("叫~~")
        listener?.OnListenermY()
    }


    interface ClickListener {
        fun OnListenermY()
    }

    fun setMyOnCli(listener: ClickListener) {
        this.listener = listener
    }


}