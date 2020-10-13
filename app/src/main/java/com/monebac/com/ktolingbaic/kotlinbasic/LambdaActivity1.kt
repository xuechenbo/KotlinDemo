package com.monebac.com.ktolingbaic.kotlinbasic

import com.monebac.com.R
import com.monebac.com.base.BaseActivity
import kotlinx.android.synthetic.main.act_lambda.*

class LambdaActivity1 : BaseActivity() {

    override fun initLayout(): Int {
        return R.layout.act_lambda
    }

    override fun initData() {
        bt1.setOnClickListener {

            maxCustom { s, s1 ->
                s
            }
            maxCustom1 { s, s1 ->
                s1
            }

        }
    }

    lateinit var mListener: (String) -> String
    //private var mListener = { str: String -> str }

    fun setSuccess(mlistener: (String) -> String) {
        mListener = mlistener
    }


    //TODO 对于函数类型的参数，你要指明它有几个参数、参数的类型是什么以及返回值类型是什么，
    //TODO 大概这个样子：
    fun a(funParam: (Int) -> String): String {
        return funParam(1)
    }

    //TODO 同样的，函数类型不只可以作为函数的参数类型，还可以作为函数的返回值类型：
//    fun c(param: Int): (Int) -> Unit {
//
//    }


    private fun maxCustom(greater: (String, String) -> String): String? {
        var max: String? = null
        return max
    }

    private fun maxCustom1(greater: (String, Boolean) -> Boolean): String? {
        var max: String? = null
        return max
    }

    /**
     *
     * lambda表达式始终用花括号包围，并用 -> 将参数列表和函数主体分离。当lambda自行进行类型推导时，
     * 最后一行表达式返回值类型作为lambda的返回值类型。现在一个函数必需的参数列表、函数体和返回类型都一一找出来了
     *
     * 都说可以将函数作为变量值传递，那该变量的类型如何定义呢？函数变量的类型统称函数类型，
     * 所谓函数类型就是声明该函数的参数类型列表和函数返回值类型。
     *
     */


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

    //当显式声明lambda的函数类型时，可以省去lambda参数列表中参数的类型，
    //并且最后一行表达式的返回值类型必须与声明的返回值类型一致:   a:  b: 可以省去

    val min: (a: Int, b: Int) -> Int = { x, y ->
        //只能返回Int类型，最后一句表达式的返回值必须为Int
        //if表达式返回Int
        if (x < y) {
            x
        } else {
            y
        }
    }

}