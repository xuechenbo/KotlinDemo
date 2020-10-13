package com.monebac.com.ktolingbaic.kotlinbasic

import com.monebac.com.utils.LogsUtils

/**
 * 单例模式，
 * 私有构造函数
 * 通过静态字段对外提供实例
 * 静态代码块中直接初始化，线程安全 。
 * 查看字节码文件:
 * 单例对象的方法是 ObjectKotlin.INSTANCE ，
 * 即调用 ObjectKotlin 类的静态字段 INSTANCE，就会触发类的初始化阶段，也就触发了 static 代码块的执行，
 * 从而完成了单例对象的实例化。同时，由于类加载过程天生线程安全
 * Kotlin 的 object 单例就是一个线程安全的懒汉式单例(访问时初始化)。
 *
 *
 * object 声明的单例类和普通类一样，可以实现接口，继承类，也可以包含属性，方法。但是它不能由开发者手动声明构造函数，
 * 从反编译出来的 Java 代码可以看到，它只有一个 private 构造函数。
 */
object ObjectKotlin {
    fun getColor() {
        LogsUtils.e("abc")
    }
}