package com.monebac.com.ktolingbaic.kotlinbasic

import android.content.res.Resources
import com.monebac.com.R
import com.monebac.com.ktolingbaic.kotlinbasic.model.AddressModel
import com.monebac.com.base.BaseActivity
import com.monebac.com.utils.DateUtil
import com.monebac.com.utils.LogsUtils
import kotlinx.android.synthetic.main.activity_function.*
import kotlinx.android.synthetic.main.layout_title.*
import org.jetbrains.anko.startActivity
import java.util.*

class FunctionActivity : BaseActivity() {
    override fun initLayout(): Int {
        return R.layout.activity_function
    }

    override fun initData() {
        tv_title.text = "函数"
        back.setOnClickListener { finish() }

        initListener()
    }


    //TODO let run with also appl 当做临时范围函数？
    fun initListener() {
        //TODO  let  最常用的场景就是使用let函数处理需要针对一个可null的对象统一做判空处理。
        // 在函数体内使用it替代object对象去访问其公有的属性和方法
        bt1.setOnClickListener {
            var empty: String? = null
            empty.let {
                LogsUtils.e(it)
            }
            empty = "赋值一次"
            //不为空 就执行
            empty?.let {
                LogsUtils.e(it)
            }
        }

        bt2.setOnClickListener {
            //TODO  with  适用于调用同一个类的多个方法时，可以省去类名重复，直接调用类的方法即可，
            // 经常用于Android中RecyclerView中onBinderViewHolder中，数据model的属性映射到UI上
            var addressModel = AddressModel()
            val addressConten: String? = with(addressModel) {
                LogsUtils.e("我的地址是$address | ${this.cityId}")
                "我的地址是$address | ${this.cityId}"
            }
            LogsUtils.e(addressConten)
        }

        //TODO run 相当于let和with的结合体,返回值未语句块的最后一行
        bt3.setOnClickListener {
            var runString: String? = null
            kotlin.run { LogsUtils.e("没有.的run，默认Kotlin.run") }
            var inA = runString.run {
                LogsUtils.e("$runString+run")
                1
            }
            runString = "赋值后"
            //不为空执行
            var inString = runString?.run {
                LogsUtils.e("$runString+?run")
                "102"
            }
            var addModel = runString.run {
                runString = "?"
                var addressModel = AddressModel()
                addressModel
            }
            LogsUtils.e(runString)
        }

        //TODO apply   整体作用功能和run函数很像，唯一不同点就是它返回的值是对象本身
        bt4.setOnClickListener {
            var appString = AddressModel()

            appString.apply {


            }

            appString?.apply {

            }

            var a: String = "1"
            var b: String? = null
            var c: String = "3"

            var cont = a?.apply {
                LogsUtils.e(a)
                a
            }?.apply {
                LogsUtils.e(b)
                b
            }?.apply {
                LogsUtils.e(c)
                c
            }

            LogsUtils.e(cont)

            //TODO  输出结果  1  null 3  1
        }

        bt5.setOnClickListener {
            //TODO also函数的结构实际上和let很像唯一的区别就是返回值的不一样，返回函数体内最后一行的值
            //TODO 如果最后一行为空就返回一个Unit类型的默认值。
            //TODO 而also函数返回的则是传入对象的本身

            //TODO 可以
//            startActivity(Intent(this, RegisterActivity::class.java)
//                    .apply { this.putExtra("isType", "测试also") })

//            val intent = Intent(context, RegisterActivity::class.java)
//            intent.putExtra("abc","")
//            startActivity(intent)
        }

        //TODO 扩展函数
        bt6.setOnClickListener {
            var adb = 100.dp2px()
            LogsUtils.e("你好$adb")

            var add = AddressModel()


            var arrayOf = arrayOf("abc", "add", "aaa")


            arrayOf.swap(0, 1)
            for (item in arrayOf) {
                LogsUtils.e(item)
            }

        }

        //TODO   扩展属性
        bt7.setOnClickListener {
            var AddressModel = AddressModel()
            LogsUtils.e(AddressModel.age)
            //
            val range = 0..10
            LogsUtils.e("区间:$range")
        }



        bt8.setOnClickListener {
            //third="" ,制定参数复制，直接指定第三个参数,跳过了第二个默认参数
            getDemo(1, third = "第三个值")

            getKbi("1", "?", "s")

            getzH("第一个参数", "", "", arrayOf("sabc", "abc"))

            //TODO 泛型函数
            append("第一", 2, "string", arrayListOf("abc", "cdef"), arrayOf("abc"))

            //TODO 5!=5*4*3*2*1  普通递归
            LogsUtils.e(getjC(5).toString())

            //TODO  关键字 tailrec尾递归函数
            LogsUtils.e("tailrec尾递归函数" + getDg(5).toString())
        }

        //TODO 日期函数
        bt10.setOnClickListener {
            DateUtil.nowDate

        }


        //TODO 高阶函数与Lambda表达式
        bt9.setOnClickListener {
            startActivity<LambdaActivity>()
            startActivity<LambdaActivity1>()
        }


    }


    val sum: (Int, Int) -> Int = { x: Int, y: Int -> x + y }

    var AddressModel.age: String
        get() = "扩展属性get"
        set(value) {
            //set方法并没有field可以用来存储value，
            //其实际作用是使用通过value来操作调用者，即this
            LogsUtils.e("扩展属性的set方法")
        }


    tailrec fun getDg(abc: Int): Int {
        return if (abc <= 1) abc else abc * getjC(abc - 1)
    }

    fun getjC(abc: Int): Int = if (abc <= 1) abc else abc * getjC(abc - 1)

    fun <T> append(first: String, vararg otherInfo: T?, second: String = "默认参数二") {

        otherInfo.forEach {
            when (it) {
                is Int -> {
                    LogsUtils.e("这是一个Int$it")
                }
                is ArrayList<*> -> {
//                    it.forEach {
//                        LogsUtils.e("?")
//                    }
                    LogsUtils.e("$it")
                }
                is Array<*> -> {
                    LogsUtils.e("$it")
                }
                is String -> {
                    LogsUtils.e("这是一个字符串$it")
                }
            }
        }

    }

    //TODO 可变参数  !!!!!!!!!!!! 关键字  vararg!!!!!!!!!!!
    fun getKbi(vararg item: String) {
        item.forEach { LogsUtils.e(it) }
    }

    //TODO 有默认参数 可以不传
    fun getDemo(first: Int, second: String = "默认第二个值", third: String = "") {

    }

    fun getzH(first: String, second: String = "第二个", third: String = "第三个", vararg array: Array<String>) {

    }

    fun Int.dp2px(): Float {
        return (0.5f + this * Resources.getSystem().displayMetrics.density)
    }


    //泛型扩展函数
    open fun <T> Array<T>.swap(one: Int, two: Int) {
        val tmp = this[one]
        this[one] = this[two]
        this[two] = tmp
    }


}