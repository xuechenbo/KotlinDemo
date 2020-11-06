package com.monebac.com.ktolingbaic.kotlinbasic

import android.content.Context
import android.content.Intent
import android.widget.Button
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.OrientationHelper
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.monebac.com.R
import com.monebac.com.base.BaseActivity
import kotlinx.android.synthetic.main.activity_kotlin.*
import kotlinx.android.synthetic.main.layout_title.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class KotlinActivity : BaseActivity() {

    lateinit var myAdapter: MyAadapter

    override fun initLayout(): Int {
        return R.layout.activity_kotlin
    }

    override fun initData() {
        tv_title?.run { text = "列表" }
        back.setOnClickListener { finish() }
        val arrayListOf = arrayListOf("变量", "函数", "类", "MaterialDesign", "list的操作符")
        recyCler.addItemDecoration(DividerItemDecoration(context, OrientationHelper.VERTICAL))
        myAdapter = MyAadapter(arrayListOf, context)
        recyCler.adapter = myAdapter
        initListener()

        myAdapter.setOnItemClickListener { adapter, view, position ->
            when (position) {
                0 -> {
                    startActivity(Intent(context, VarStringActivity::class.java))
                }
                1 -> {
                    startActivity(Intent(context, FunctionActivity::class.java))
                }
                2 -> {
                    startActivity(Intent(context, KotlinClassActivity::class.java))
                }
                3 -> {
                    startActivity<MaterialDesignActivity>()
                }
                4 -> startActivity<ListFilterActivity>()
            }


        }

    }

    class MyAadapter(data: List<String>, context: Context) : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_kotlin, data) {
        override fun convert(helper: BaseViewHolder, item: String) {
            helper.setText(R.id.tv_cont, item)
        }
    }


    private fun initListener() {
        bt_other.setOnClickListener {
            //            val alterDialog = AlertDialog.Builder(this)
//            alterDialog.run {
//                setTitle("温馨提示")
//                setMessage("这是一个测试的提示框")
//                setPositiveButton("卸载") { _, _ ->
//
//                }
//                setNeutralButton("在等等") { _, _ ->
//
//                }
//            }
//            alterDialog.show()

            //TODO Anto库的封装
            alert("这是一个测试的提示框", "温馨提示") {
                //                yesButton {
//                    toast("yesButton")
//                }
//                noButton {
//                    toast("noButton")
//                }
                positiveButton("卸载") {

                }
                negativeButton("再等等") {

                }
            }.show()
        }


        bt_other.setOnLongClickListener { v ->
            val button = v as Button
            button.text = "长按事件"
            toast("长按事件")

            //TODO Anto  如果跳转的时候想设置启动模式  intentFor 可以返回一个intent
            //TODO page 299
//            val intentFor = intentFor<MainKtpActivity>()
//            //类似默认启动模式，但是如果原来不存在活动栈，就会创建一个新的栈
//            startActivity(intentFor.newTask())
//            //就是singletop栈顶复用
//            startActivity(intentFor.singleTop())
//            //跳转新页面，栈中原有的都清空
//            startActivity(intentFor.clearTask())
//            //类似默认启动模式，但栈中不保留新启动的实例
//            startActivity(intentFor.noHistory())
//            //栈中存在待跳转的act，会重新创建一个实例，姜原来上的全部清除  区别:singtask onNewIntent启用，这个是onDestory 在create
//            startActivity(intentFor.clearTop())

            true

        }
    }


}