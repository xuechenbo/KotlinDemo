package com.monebac.com.ktolingbaic.kotlinbasic.model

import com.monebac.com.utils.LogsUtils

class CatModel : CalloutModel(), CalloutInterface {

    //实现接口属性
    override var skilledSports: String = "接口属性:嘤嘤嘤"

    //实现接口方法
    override fun getB() {
        LogsUtils.e("抓老鼠了")
    }

    override fun getCall() {
        LogsUtils.e("叫~~")
    }
}