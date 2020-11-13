package com.monebac.com.jetpack.coroutines

import com.monebac.com.R
import com.monebac.com.base.BaseActivity
import kotlinx.android.synthetic.main.act_coroutines.*
import kotlinx.android.synthetic.main.layout_title.*
import kotlinx.coroutines.*
import org.jetbrains.anko.startActivity

class CoroutinesActivity : BaseActivity() {

    var strContent = ""

    override fun initLayout(): Int {
        return R.layout.act_coroutines
    }

    //让我来补位
    override fun initData() {
        back.setOnClickListener {
            finish()
        }

        tv_content.setOnClickListener {
            startActivity<CoroutinesDemoActivity>()
        }


        tv_title.text = "conroutines"
//        init()
//        init1()
        init2()
    }

    private fun init2() {
        //并发请求数据，结果统一展示   value 先执行完，并发执行value1和value2 1和2的结果更新UI
        GlobalScope.launch(Dispatchers.Main) {
            val value = request()
            val value1 = async { request1() }
            val value2 = async { request2() }
            updateUI(value1.await(), value2.await())
        }
    }

    private suspend fun request(): String {
        withContext(Dispatchers.IO) {
            delay(2000)
            strContent += "同步-"
        }
        return strContent
    }

    private suspend fun request1() {
        withContext(Dispatchers.IO) {
            strContent += "异步1-"
        }
    }

    private suspend fun request2() {
        withContext(Dispatchers.IO) {
            strContent += "异步2-"
        }
    }

    private fun updateUI(await: Unit, await1: Unit) {
        tv_content.text = strContent
    }

    private fun init() {

        GlobalScope.launch(Dispatchers.Main) {
            println("Hello ${Thread.currentThread().name}")
            test()
            println("End ${Thread.currentThread().name}")
        }
        //TODO 顺序: hello   两秒之后 world end
    }

    //suspend 使用上相当于标志了一个耗时操作
    //挂起函数不会造成线程阻塞
    private suspend fun test() {
        //withContext进行了线程切换
        withContext(Dispatchers.IO) {
            delay(2000)
            println("World ${Thread.currentThread().name}")
        }
    }


    private fun init1() {
        println("Start ${Thread.currentThread().name}")
        //指定运行的线程为主线程
        //
        GlobalScope.launch(Dispatchers.Main) {
            //延迟 delay
            delay(1001L)
            println("Hello World Dispatchers.Main${Thread.currentThread().name}")
        }
        println("End")

        //TODO 输出结果：  start     end   hellow world
        //TODO 自动切换了线程
    }


}