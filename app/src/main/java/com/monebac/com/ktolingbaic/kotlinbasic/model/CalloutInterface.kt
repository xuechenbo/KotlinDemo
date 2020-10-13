package com.monebac.com.ktolingbaic.kotlinbasic.model

import com.monebac.com.utils.LogsUtils

interface CalloutInterface {

    /**
     * Kotlin和java的区别在 ，kotlin接口内部允许实现方法
     *
     * Kotlin 不能同时继承多个类
     *
     * 接口内部的方法默认是abstract,可以省略，open也一样
     *
     * 还可以声明抽象属性，实现该接口的类，必须重载该属性
     */


    var skilledSports: String

    fun getA() {
        LogsUtils.e("接口内部实现方法")
    }


    fun getB()
}