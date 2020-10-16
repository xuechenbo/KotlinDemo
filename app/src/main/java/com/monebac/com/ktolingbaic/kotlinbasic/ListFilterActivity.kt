package com.monebac.com.ktolingbaic.kotlinbasic

import com.monebac.com.R
import com.monebac.com.base.BaseActivity
import kotlinx.android.synthetic.main.activity_list_filter.*

class ListFilterActivity : BaseActivity() {

    override fun initLayout(): Int {
        return R.layout.activity_list_filter
    }

    override fun initData() {

        //TODO    //https://www.jianshu.com/p/82270112ac6b

        val arrayOf = arrayOf("一个", "两个")

        val mList = mutableListOf("紫色", "白色", "蓝色", "红色", "粉色", "黑色", "绿色")

        /**
         * TODO 我们声明一个集合或者数组，可以转换成相应类型的集合。
         * 调用toXXX()转换成不可变集合。调用toMutableXXX()转换为可变集合。集合类提供了toList()、
         * toMutableList()、toSet()、toMutableSet()、toHashSet()、toMap()等高阶函数去处理。
         */
        bt1.setOnClickListener {
            val toList = arrayOf.toList()
            val toMutableList = arrayOf.toMutableList()
            println(toList)
            println(toMutableList)
        }


        bt2.setOnClickListener {
            //any 判断是否有满足条件的元素
            println(mList.any {
                it == "白色"
            })

            //是否都满足条件
            println(mList.all {
                it is String
            })

            //是否都不满足条件
            println(mList.none {
                it is String
            })

            //满足条件的元素个数
            println(mList.count {
                it is String
            })

            //第一项到最后一个的累加
            println(mList.reduce { acc, s ->
                acc + s
            })

            //最大的元素
            println(mList.max())

            //没懂哈
            //从第一项开始，去掉满足条件的元素，直到不满足条件的一项为止
            val dropWhile = mList.dropWhile {
                it == "紫色"
            }
            println(dropWhile)
        }


        bt3.setOnClickListener {
            val mList = mutableListOf(2, 1, 4, 3, 5, 7, 8, 6)
            //过滤满足条件的所有元素  满足条件的集合
            println(mList.filter {
                it > 5
            })
            //不满足条件的集合
            println(mList.filterNot {
                it > 5
            })

            //前几个
            println(mList.take(5))
            //后几个
            println(mList.takeLast(5))
            //不满足条件的下标前面的所有元素的集合  ？？？？
            println(mList.takeWhile {
                it < 5
            })

            //去掉前n个元素后的列表
            println(mList.drop(5))


        }

        bt4.setOnClickListener {
            val mList = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8)
            val list = listOf(1, 2, 30, 40)

            // 将集合中的元素通过某个 方法转换 后的结果存到一个集合中;
            println(mList.map {
                println(it)
                if (it < 5) it + 1 else it + 2
            })

            //除了得到 转换后的结果 ，还可以拿到Index(下标);  i数值
            println(mList.mapIndexed { index, i ->
                //println(index)
                //println(i)
                index + i
            })


            //按某个条件分组
            println(mList.groupBy {
                if (it / 2 == 0) "偶数" else "奇数"
            })

            //合并两个集合
            println(listOf(mList, list).flatMap {
                it
            })
        }


        //元素操作
        bt7.setOnClickListener {
            val mList = arrayOf("一个", "两个", 1, 2, 3, 6)

            println(mList.contains(1))


            //查找下标对应的元素
            println(mList.elementAt(1))
            //下标对应的元素，越界返回0
            println(mList.elementAtOrElse(8) {
                -1
            })
            //越界返回null
            println(mList.elementAtOrNull(9))


            //第一个满足条件的
            println(mList.first {
                it.toString().length == 2
            })
            //没有返回null
            println(mList.firstOrNull {
                it.toString().length == 3
            })

            //符合条件的最后一个 没有 返回null
            println(mList.lastOrNull {
                it == 15
            })
            //返回符合条件的最后一个元素，没有 返回-1
            println(mList.lastIndexOf(14))


            //返回指定下标的元素，没有返回-1
            println(mList.indexOf(8))
            //第一个符合条件的元素 ! 下标 !,没有-1
            println(mList.indexOfFirst {
                it == 6
            })
            println(mList.indexOfLast {
                it == 6
            })


        }


    }
}