package com.monebac.com.utils

import com.monebac.com.ktolingbaic.kotlinbasic.model.AddressModel
import java.text.SimpleDateFormat
import java.util.*


/**
 *  关键字  objcet   修饰的类为静态类，里面的方法和变量都是静态的
 *  object 声明一个类时，表明这个类是一个单例类?
 *  不能用在方法和inner内部类中，但是能嵌套在其他object声明和嵌套类中
 *  ojbect定义后即刻实例化，
 *  companion object 修饰的为伴生对象，在类中只能存在一个，
 */

object DateUtil {
    val nowDateTime: String
        get() {
            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            return sdf.format(Date())
        }

    val nowDate: String
        get() {
            val sdf = SimpleDateFormat("yyyy-MM-dd")
            return sdf.format(Date())
        }

    val nowTime: String
        get() {
            val sdf = SimpleDateFormat("HH:mm:ss")
            return sdf.format(Date())
        }

    val nowTimeDetail: String
        get() {
            val sdf = SimpleDateFormat("HH:mm:ss.SSS")
            return sdf.format(Date())
        }

    fun getFormatTime(format: String = ""): String {
        val ft: String = format
        val sdf = if (!ft.isEmpty()) SimpleDateFormat(ft)
        else SimpleDateFormat("yyyyMMddHHmmss")
        return sdf.format(Date())
    }


    var AddressModel.age: String
        get() = "扩展属性get"
        set(value) {
            //set方法并没有field可以用来存储value，
            //其实际作用是使用通过value来操作调用者，即this
            LogsUtils.e("扩展属性的set方法")
        }
}