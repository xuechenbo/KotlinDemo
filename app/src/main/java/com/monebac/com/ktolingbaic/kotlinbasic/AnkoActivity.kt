package com.monebac.com.ktolingbaic.kotlinbasic

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk27.coroutines.onClick


/**
 * Anko  四部分
 * 1，Anko Commons
 * 轻量级的一些帮助类，比如 intent，dialog，logging 等等，其实就是对安卓一些类：Activity、Fragment、Intent 等添加扩展函数。
 * 2，Anko Layouts
 * 动态布局用的最主要的库，将许多 Android 的控件 View 转换成了 Anko 加载的形式。
 * 由于 Android 还有其他的控件库，因此 Anko 也对那些库进行了拓展支持，可以选择添加对应的依赖库。
 * 当然，还可以根据需要对自定义 View 进行改造，让它们也支持 Anko 加载的形式。
 * 3，Anko SQLite
 * 用于 Android SQLite 数据库的查询的库
 * 4，Anko Coroutines
 * 基于 kotlinx.coroutines 协程的一个工具库。
 *
 *
 */
class AnkoActivity : AppCompatActivity() {

    //TODO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        verticalLayout {
            padding = dip(8)
            val account = textView("account") {
                textSize = sp(12).toFloat()
                // 设置自己的属性
            }.lparams {
                // 设置布局的参数
            }
            var name = editText() // 也可以什么都不设置，使用默认的设置
            textView("password") {
                onClick {
                    account.text = "change account"
                }
            }
            var pwd = editText {
                hint = "input"
            }.lparams(
                    width = dip(100),
                    height = ViewGroup.LayoutParams.WRAP_CONTENT
            )
            button("login") {
                onClick {
                    if (name.text.toString() == "name"
                            && pwd.text.toString() == "pwd") {
                        longToast("login....")
                    } else {
                        longToast("login failed")
                    }
                }
            }
        }
    }


}