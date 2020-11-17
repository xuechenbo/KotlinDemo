package com.monebac.com.wkyk.ui

import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.monebac.com.R
import com.monebac.com.base.BaseActivity
import com.monebac.com.utils.getMap
import com.monebac.com.wkyk.adapter.LookPlanAdapter
import com.monebac.com.wkyk.model.BindCard
import com.monebac.com.wkyk.model.PlanAllModel
import com.monebac.com.wkyk.viewmodel.LookPlanViewModel
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import kotlinx.android.synthetic.main.activity_bank_list.*
import kotlinx.android.synthetic.main.layout_title.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class LookPlanActivity : BaseActivity() {
    lateinit var bindCard: BindCard
    private lateinit var mList: List<PlanAllModel>
    private lateinit var lookPlanAdapter: LookPlanAdapter

    private val viewModel by lazy {
        ViewModelProvider(this)[LookPlanViewModel::class.java]

//        ViewModelProviders.of(this).get(LookPlanViewModel::class.java)
    }

    override fun initLayout(): Int = R.layout.activity_look_plan

    override fun initData() {

        bindCard = intent.getSerializableExtra("BindCard_Class") as BindCard
        mList = ArrayList()
        back.setOnClickListener { finish() }
        tv_title.text = "查看计划"
        lookPlanAdapter = LookPlanAdapter(mList)
        recyCler.adapter = lookPlanAdapter
        initListener()

        viewModel.getListData(
                getMap(mutableMapOf(
                        "42" to getMerNo(),
                        "3" to "190212",
                        "43" to bindCard.BANK_ACCOUNT))
        )
    }

    val initListener = {
        viewModel.lookup.observe(this, Observer {
            Log.e("详情：", it.toString())
            lookPlanAdapter.setNewData(it)
        })

        viewModel.fail.observe(this, Observer {
            toast(it)
        })

        smarFresh.run {
            refreshHeader = ClassicsHeader(context)
            isEnableLoadmore = false
            setOnRefreshListener {

            }
        }

        lookPlanAdapter.setOnItemChildClickListener { _, view, position ->
            when (view.id) {
                R.id.btn_detail -> startActivity<PlanDetailActivity>(
                        "planDetail" to mList[position],
                        "bindCard" to bindCard)
            }
        }
    }

}