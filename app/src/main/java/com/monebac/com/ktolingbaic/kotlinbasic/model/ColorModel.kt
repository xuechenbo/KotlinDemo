package com.monebac.com.ktolingbaic.kotlinbasic.model

import com.monebac.com.utils.LogsUtils


//TODO 扩展属性，get方法?
class ColorModel(var name: String, var birthday: Int) {

    fun eat() {
        LogsUtils.e("I'm going to eat $name$birthday")
    }

    fun sleep() {
        LogsUtils.e("I'm going to sleep$name$birthday")
    }

}