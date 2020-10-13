package com.monebac.com.ktolingbaic.kotlinbasic

import com.monebac.com.R
import com.monebac.com.base.BaseActivity
import com.monebac.com.ktolingbaic.kotlinbasic.model.*
import com.monebac.com.utils.LogsUtils
import kotlinx.android.synthetic.main.activity_class.*
import kotlinx.android.synthetic.main.layout_title.*

class KotlinClassActivity : BaseActivity() {
    /**
     *  Kotlin 省略了关键字 public，默认是开放的
     *  用: 代替 extends
     *  进行继承的时候，父类后面添加()
     */

    override fun initLayout(): Int {
        return R.layout.activity_class
    }


    override fun initData() {

        back.setOnClickListener { finish() }
        tv_title.text = "类"

        bt1.setOnClickListener {
            var addressModel = AddressModel("1", "2", "3")

            //class  的属性
            var personModel = PersonModel("王舞", "女")
            LogsUtils.e("${personModel.name}${personModel.sex}")
            LogsUtils.e(personModel.getPersonMsg())

        }

        bt2.setOnClickListener {
            var personModel = PersonModel("王舞", "女")

            //两种都可以
            LogsUtils.e(PersonModel.judgeSex("女").toString())
            LogsUtils.e(PersonModel.WildAnimal.judgeSex("男").toString())
            LogsUtils.e(PersonModel.type.toString())
            LogsUtils.e(PersonModel.flag.toString())

        }

        bt3.setOnClickListener {
            var childMode = ChildModel("豆子", "女", "15岁")
            LogsUtils.e(childMode.getPersonMsg())

            //抽象abstact
            var catModel = CatModel()
            catModel.getCall()

            //接口interface
            catModel.getA()
            catModel.getB()
            LogsUtils.e(catModel.skilledSports)
        }


        //TODO 代理接口 不懂~~~~~~~~~~~~~~~~~~~~~
        bt4.setOnClickListener {
            var theAgent = TheAgent(call = DogModel())
            theAgent.getB()
        }

        //TODO 枚举类
        bt5.setOnClickListener {
            LogsUtils.e(Season.SPRING.str)                  //春
            LogsUtils.e(Season.SPRING.name)                 //SPRING
            LogsUtils.e(Season.SPRING.ordinal.toString())   //0
        }

        //var list = Gson().fromJson<List<BeanModel>>("abc", object : TypeToken<List<BeanModel>>() {}.type)

    }

    //TODO 关键字 inner 内部类 可以访问外部类成员
    inner class model {

        var name: String? = null
        var age: String? = null

        fun getId() {
            LogsUtils.e("${bt1.text} 测试的")
        }

//        val mList = Gson().fromJson<List<BeanModel>>("acc", object : TypeToken<List<BeanModel>>() {
//
//        }.type)

    }


    //TODO 枚举类  可有构造方法
    enum class Season(var str: String) {
        SPRING("春"),
        SUMMER("夏"),
        AUTUMN("秋"),
        WINTER("冬")
    }
}
