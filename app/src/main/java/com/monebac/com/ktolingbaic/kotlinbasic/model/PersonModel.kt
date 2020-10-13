package com.monebac.com.ktolingbaic.kotlinbasic.model


//插件 JsonToKotlin   alt+k
//加上var  val  的前缀，表示同时声明与该参数同名的属性
open class PersonModel(var name: String, var sex: String) {

    open fun getPersonMsg() = "$name$sex"

    /**
     * Kotlin没有关键字 static  伴生对象理解为static
     * companion 表示伴随，objcet表示对象
     * 伴生对象实现静态函数，静态属性    伴随着类而存在的对象，在类加载的时候初始化
     *
     */
    companion object WildAnimal {

        val type = 0
        val flag = 1

        fun judgeSex(sexName: String): Int {
            var sex: Int = when (sexName) {
                "女" -> 0
                "男" -> 1
                else -> -1
            }
            return sex
        }
    }


}

