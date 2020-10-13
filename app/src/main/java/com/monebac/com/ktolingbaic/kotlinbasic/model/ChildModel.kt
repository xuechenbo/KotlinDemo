package com.monebac.com.ktolingbaic.kotlinbasic.model


/**
 * Kotlin默认每个类是不能被继承的，相当于java的 +了final，如果想让被继承就需要开放这个类
 * 关键字 open
 *
 * open 不控制下面四个的访问权限，只控制能否有子类，函数没有不带open，子类不能修改
 *
 * 但是open和private不能共存？？？
 *
 * public 所有人开放，默认为pubic
 * intemal 只对本模块内部开放，本模块指app自身
 * protected 对自己和子类开放
 * private 私有
 *
 */
class ChildModel(name: String, sex: String, var age: String) : PersonModel(name, sex) {
    //父类  函数有open
    override fun getPersonMsg() = "$name,$sex,$age"

}