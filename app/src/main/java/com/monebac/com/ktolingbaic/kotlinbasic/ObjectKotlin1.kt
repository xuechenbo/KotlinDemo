package com.monebac.com.ktolingbaic.kotlinbasic

import com.monebac.com.utils.LogsUtils

/**
 * 携带参数的单例
 * ompanion object
 */
//step 3
class ObjectKotlin1 private constructor(private val param: Int) {

    //step 2
    var str = "String"

    companion object {
        @Volatile
        private var instance: ObjectKotlin1? = null

        fun getInstance(property: Int) = instance ?: synchronized(this) {
            //step 1
            instance ?: ObjectKotlin1(property).also { instance = it }
        }
    }


    fun getColor() {
        LogsUtils.e("abc")
    }
}