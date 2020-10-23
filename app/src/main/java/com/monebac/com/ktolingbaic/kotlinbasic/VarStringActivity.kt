package com.monebac.com.ktolingbaic.kotlinbasic

import android.os.Build
import androidx.annotation.RequiresApi
import com.monebac.com.R
import com.monebac.com.base.BaseActivity
import com.monebac.com.ktolingbaic.kotlinbasic.model.ColorModel
import com.monebac.com.utils.LogsUtils
import com.monebac.com.utils.toYMD
import kotlinx.android.synthetic.main.activity_function.*
import kotlinx.android.synthetic.main.layout_title.*
import java.text.SimpleDateFormat
import java.util.*

class VarStringActivity : BaseActivity() {


    override fun initLayout(): Int {
        return R.layout.activity_var_string
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun initData() {
        back.setOnClickListener { finish() }

        //TODO 数组
        bt1.setOnClickListener {

            //var int_array: IntArray = intArrayOf(1, 2, 3)
//            var double_array: DoubleArray = doubleArrayOf(1.0, 2.23, 2.0)
//            var long_array: LongArray = longArrayOf(1, 2, 3)
//            var float_array: FloatArray = floatArrayOf(1.2f, 1.3f, 1.5f)
//            var boolean_array: BooleanArray = booleanArrayOf(true, false, false)
//            var string_array: Array<String> = arrayOf("How", "Are", "You")

            //数组的定义
            var strArray = arrayOf("qaq,qwq,aqa,wqw", "abd", "223")
            var int_array: Array<Int> = arrayOf(1, 2, 3)
            var long_array: Array<Long> = arrayOf(1, 2, 3)
            var float_array: Array<Float> = arrayOf(1.0f, 2.0f, 3.0f)
            var double_array: Array<Double> = arrayOf(1.0, 2.0, 3.0)
            var boolean_array: Array<Boolean> = arrayOf(true, false, true)
            var char_array: Array<Char> = arrayOf('a', 'b', 'c')

            var obj_array: Array<Any> = arrayOf(1, "s", "s", 2)

            //数组转化为list
            val asList = Arrays.asList(*obj_array)


            LogsUtils.e(strArray.size.toString())

            LogsUtils.e(strArray[1])

            LogsUtils.e("${strArray.get(2).toInt()}")


            //字符串的replace  indexof，substring，splite方法
            val content = strArray[0]

            val replace = content.replace("qaq", "测试哈")
            LogsUtils.e(replace)


            val indexOf = content.indexOf("qwq", 1)
            LogsUtils.e(indexOf.toString())

            LogsUtils.e(content.substring(1, 3))


            //分隔
            val split = content.split(",")
            for (item in split) {
                LogsUtils.e(item)
            }

            LogsUtils.e("\$ $content")

        }


        //TODO 集合  Set  Map  List
        bt2.setOnClickListener {
            //TODO  PAGE 91
            //TODO 可变集合 (mutableList、arrayListOf,mutableSetOf、hashSetOf、linkedSetOf、sortedSetOf,
            //TODO         mutableMapOf、hashMapOf、linkeMapOf、sortedMapOf)
            //TODO 只读集合 (listOf,setOf,mapOf)

            //TODO isEmpty  isNotEmpty  clear contains  iterator  count

            //TODO   集合 Set/mutableSet    page92


            //TODO ??????有区别么
            val arrayList = ArrayList<String>()
            val arrayListOf = arrayListOf<String>("abc")


            var mList: List<String> = listOf("abc")

            var mlist: List<String> = listOf()

            val ofList = setOf("1", "2", "3", "4")


            //TODO 三种遍历
            var content = ""
            for (item in ofList) {
                content = "$content 名称:$item \n"
            }
            LogsUtils.e(content)


            var content1 = ""
            val iterator = ofList.iterator()
            while (iterator.hasNext()) {
                val next = iterator.next()
                content1 = "$content1 名称:$next \n"
            }
            LogsUtils.e(content1)

            var content2 = ""
            ofList.forEach { content2 = "$content2 名称 $it  \n" }

            LogsUtils.e(content2)


            //TODO 可变集合下标遍历    关键字 indices
            for (i in arrayListOf.indices) {
                arrayListOf[i]
            }
        }

        //TODO 集合排序
        var isSort = false
        bt3.setOnClickListener {
            var arrayListOf = mutableListOf("3", "6", "2", "5")
            LogsUtils.e(arrayListOf.toString())

            //排序   sortBy 升序  sortByDescending降序
            if (isSort) {
                arrayListOf.sortBy { it.toInt() }
            } else {
                arrayListOf.sortByDescending { it.toInt() }
            }

            LogsUtils.e(arrayListOf.toString())

            var content = ""
            arrayListOf.forEach { content = "$content : $it" }

            LogsUtils.e("按照${if (isSort) "升序" else "降序"}排序,从新排列 $content")

            isSort = !isSort

        }


        //TODO map
        bt4.setOnClickListener {

            val mapOf = mapOf("苹果" to "iphone11", "华为" to "p40 Pro", "小米" to "Mini12")

            var content = ""
            mapOf.forEach { (key, value) ->
                content = "$content$key 生产 $value"
            }
            LogsUtils.e(content)

            var content1 = ""
            for (item in mapOf) {
                content1 = "$content1 ${item.key} 生产 ${item.value}"
            }
            LogsUtils.e(content1)

            var content2 = ""
            val iterator = mapOf.iterator()
            while (iterator.hasNext()) {
                val next = iterator.next()
                content2 = "$content2 ${next.key} 生产 ${next.value}"
            }
            LogsUtils.e(content2)

        }

        //TODO  控制语句
        bt5.setOnClickListener {

            //11-14  不包括15
            for (i in 11 until 15) {
                LogsUtils.e(i.toString())
            }
            //11-15
            for (i in 11..15) {
                LogsUtils.e(i.toString())
            }
            //11 16 21
            for (i in 11..23 step 5) {
                LogsUtils.e(i.toString())
            }

            //9---3    不包括10
            for (i in 10 downTo 3) {
                LogsUtils.e(i.toString())
            }

            out1@ for (i in 1..50) {
                LogsUtils.e("第一个循环$i")
                if (i == 15) {
                    out2@ for (l in 2..25) {
                        LogsUtils.e("第二个循环$l")
                        if (l == 15) break@out1 else LogsUtils.e(l.toString())
                    }
                }
            }
        }

        //TODO 空
        bt6.setOnClickListener {

            /**
             *  isNullOrEmpty   空指针，字符串长度为0            true
             *  isNullOrBlank   空指针，字符串长度为0或者全为空格  true
             *  isEmpty         字符串长度为0  true
             *  isBlank         字符串长度为0 全为空格  true
             *  isNotEmpty      字符串长度大于0     true
             *  isNotBlank      字符串长度大于0 && 不是全空格   true
             *
             */
            var cont1: String? = null

            if (cont1.isNullOrEmpty()) {
                LogsUtils.e(cont1)
            }


            var leng_null: Int?
            var str: String? = null

            leng_null = str?.length

            LogsUtils.e("使用?.可以获取null的值 $leng_null")
            val i = str?.length ?: 1

            //TODO ?.+?:  相当于三元运算符？？
            LogsUtils.e("使用?. ?:可以获取null的值 $i")

            //TODO !! 表示不做非空判断，强制执行后面的语句，如果为空，报空异常
            var strB: String? = null
            var leng: Int?

            strB = "复制后"
            leng = strB!!.length
            LogsUtils.e("!!强行非空执行 $leng")
        }


        //2*2=4

        bt7.setOnClickListener {
            var str1 = "abc%100%"

            LogsUtils.d(str1.last().toString())

            //第一个满足条件的提替换
            LogsUtils.d(str1.replaceFirst("%", ""))

            val str = "Kotlin is a very good programming language"
            //第一个满足条件，之前的字符串 加上后面的AA
            LogsUtils.d(str.replaceAfter('a', "AA"))

            LogsUtils.d(str.replaceAfter("Kotlin", "Java"))
//           Kotlin is aAA
//           KotlinJava

            //统计重复字符串
            LogsUtils.d(str.count { it == 'a' }.toString())

            //反转字符串
            LogsUtils.d(str.reversed())
        }


        bt8.setOnClickListener {
            //三引号：Raw string
            var str = """abcde/?\
                |adf 432456(&^$^#$1asfd a
            """.trimMargin()
            LogsUtils.e(str)


            var time = 1598428780000


            LogsUtils.e(time.toYMD())

            LogsUtils.e(time.dateType)

            var come = ColorModel("小明", 1999)


            come.apply {
                name = "修改一次"
                come.eat()
            }.apply {
                birthday = 2018
                come.eat()
            }.apply {
                name = "修改第二次"
                come.eat()
            }

            come.sleep()
            LogsUtils.e("${come.age}")
        }


        bt9.setOnClickListener {

            ObjectKotlin.getColor()

            ObjectKotlin1.getInstance(1).getColor()
        }
    }


    //const
    //TODO 扩展属性
    val currentYear = 2019
    val ColorModel.age: Int
        get() = currentYear - this.birthday


    //TODO 扩展属性   返回的日期不对(记得修改)
    val Long.dateType: String
        get() = SimpleDateFormat("yyyy-mm-dd").format(this)
    //set(value)本质是没有地方存值
//        set(value) {
//
//        }


}
