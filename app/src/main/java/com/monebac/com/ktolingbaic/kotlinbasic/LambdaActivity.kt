package com.monebac.com.ktolingbaic.kotlinbasic

import com.monebac.com.R
import com.monebac.com.base.BaseActivity
import com.monebac.com.utils.LogsUtils
import kotlinx.android.synthetic.main.act_lambda.*

//TODO
class LambdaActivity : BaseActivity() {
    override fun initLayout(): Int {
        return R.layout.act_lambda
    }

    override fun initData() {

        bt1.setOnClickListener {
            val numMax = getNumMax(1, 2, 3) { str, i ->
                "$str$i"
            }
            LogsUtils.e(numMax)
        }
    }

    //(Int,Int)是参数类型，->后面的值Int是返回类型,组合在一起就构成了一个函数类型
    var sum: (Int, Int) -> Int = { x, y -> x + y }

    var sum1 = { x: Int, y: Int -> x + y }



    //TODO 1
    var action: () -> Unit = {} //无参无返回值
    lateinit var action1: () -> Unit //无参无返回值

    var action2 = {} //类型推导后的简化写法

    var action3: (Int) -> Unit = {} //有参无返回值

    var action4: () -> Int = { 1 } //无参有返回值


    //TODO 示例1
    var value = getNumResult(1) { a, b -> a + b }
    //==> value = 3

    var value1 = getNumResult(1) { a, b -> a * b }
    //==> value = 2

    var value2 = getNumResult(1) { a, b -> a - b }

    private fun getNumResult(num: Int, result: (Int, Int) -> Int): Int {
        return result(1, 2)
    }

    /**
     * 在maxCustom中，整体有一个返回类型。
     * 第一个参数 数组，第二个参数是函数参数，需要两个类型，返回一个布尔类型,
     * 在SyFun方法中使用maxCustom，
     * 我总觉得传的函数参数没有意义，理解不了啊。
     * 后来发现一点，传函数参数，可以写方法体比如maxcustom传的，str.length>str1.leng  返回一个布尔类型
     * 这个函数，可以在maxCustom中使用啊，在maxCustom中使用啊，在maxCustom中使用啊
     * 用来判断一些业务，我现在是没用到到底有什么用哈，
     * 但是传的方法体不一样，就有很多种可能了，
     * 现在还不是很懂，只是有一点思路而已。
     */

    fun SyFun() {
        val maxCustom = maxCustom(arrayOf("1", "2")) { str, str1 ->
            str.length > str1.length
        }
    }

    fun <T> maxCustom(array: Array<T>, greater: (T, T) -> Boolean): T? {
        var max: T? = null
        for (item in array)
            if (max == null || greater(item, max))
                max = item
        return max
    }


    //TODO
    fun getNumMax(num1: Int, num2: Int, num3: Int, max: (String, Int) -> String): String {

        return max("搞不懂", num2)
    }


    //函数类型
    //无参、无返回值的函数类型(Unit 返回类型不可省略)
    //() -> Unit

    //接收T类型参数、无返回值的函数类型
    //(T) -> Unit

    //接收T类型和A类型参数、无返回值的函数类型(多个参数同理)
    //(T,A) -> Unit

    //接收T类型参数，并且返回R类型值的函数类型
    //(T) -> R

    //接收T类型和A类型参数、并且返回R类型值的函数类型(多个参数同理)
    //(T,A) -> R

    //较复杂的函数类型：
    //(T,(A,B) -> C) -> R
    //一看有点复杂，先将(A,B) -> C抽出来，当作一个函数类型Y,Y = (A,B) -> C,整个函数类型就变成(T,Y) -> R。

}