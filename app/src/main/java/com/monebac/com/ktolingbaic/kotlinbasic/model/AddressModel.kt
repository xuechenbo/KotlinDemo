package com.monebac.com.ktolingbaic.kotlinbasic.model

import com.monebac.com.utils.LogsUtils
import java.io.Serializable

//vararg  可变参数

/**
 * page  189
 * 如果有多个构造函数，kotlin有主构造函数和二级构造函数
 * 关键字constructor
 */

class AddressModel /*constructor(string: String = "第一个默认参数", str: String = "第二个默认参数")*/ : Serializable {
    var address: String? = "河北省"
    var cityId: String? = "张家口市"

    /**
     *  类的初始化为init  关键字  java为构造函数
     *  init不是完整的构造函数，只是定义了初始化操作，无法直接定义输入参数。
     *  构造函数的输入参数直接加到类名后面，init仅仅表示创建类实例的初始化动作
     *
     *
     */
    /* init {
           LogsUtils.e("这是一个主构造器测试类的初始化")
       }*/


    //TODO 主构造器   主构造器不是必须的，也可以放在类的内部
    //@JvmOverloads java也能识别构造器的默认参数
    @JvmOverloads
    constructor(string: String = "第一个默认参数", str: String = "第二个默认参数") {
        LogsUtils.e("这是一个主构造器测试类的初始化")
    }

    //TODO 二级构造函数   没有函数名称
    /**
     *  如果用二级构造函数，先调用住主构造函数的init，在调用自身代码
     */
    constructor(abd: String, abc: String, abe: String) : this(abd, "") {
        LogsUtils.e("这是一个二级构造函数")
    }


}