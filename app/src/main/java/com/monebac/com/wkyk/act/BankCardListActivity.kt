package com.monebac.com.wkyk.act

import android.support.v7.widget.LinearLayoutManager
import android.view.KeyEvent
import android.view.View
import com.monebac.com.R
import com.monebac.com.base.BaseMvpActivity
import com.monebac.com.utils.getMap
import com.monebac.com.wkyk.adapter.BindCardListAdapter
import com.monebac.com.wkyk.contract.BankCardListContract
import com.monebac.com.wkyk.model.BindCard
import com.monebac.com.wkyk.presenter.BankCardPresenter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import kotlinx.android.synthetic.main.activity_bank_list.*
import kotlinx.android.synthetic.main.layout_title.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
class BankCardListActivity : BaseMvpActivity<BankCardListContract.View, BankCardPresenter>(), BankCardListContract.View {
    var mList = ArrayList<BindCard>()
    private lateinit var bindCardAdapter: BindCardListAdapter

    override fun createPresenter(): BankCardPresenter {
        return BankCardPresenter()
    }


    override fun getLayoutResId(): Int = R.layout.activity_bank_list

    override fun initView() {
        back.visibility = View.GONE
        tv_title.text = "信用卡列表"
        other.visibility = View.VISIBLE
    }

    override fun initData() {
        super.initData()
        initListener()
        presenter.getCardList(
                getMap(mutableMapOf("3" to "190932", "42" to getMerNo()))
        )
    }

    //TODO 匿名函数
    val initListener = fun() {
        other.setOnClickListener {
            //多条目布局
            startActivity<PayRecordListActivity>()
        }

        smarFresh.run {
            isEnableLoadmore = false
            refreshHeader = ClassicsHeader(context)
            setOnRefreshListener {
                presenter.getCardList(
                        getMap(mutableMapOf("3" to "190932", "42" to getMerNo()))
                )
            }
        }
        recyCler.run {
            layoutManager = LinearLayoutManager(context)
            bindCardAdapter = BindCardListAdapter(R.layout.item_bank_card, mList)
            adapter = bindCardAdapter
        }

        bindCardAdapter.run {
            setOnItemChildClickListener { _, view, postion ->
                when (view.id) {
                    R.id.tv_plan -> {
                        if (mList[postion].plancount == 0)
                            startActivity<YKchannelActivity>("BindCard_Class" to mList[postion])
                        else
                            toast("有计划执行中...")
                    }
                    R.id.tv_look_plan -> startActivity<LookPlanActivity>("BindCard_Class" to mList[postion])
                }
            }
            setOnItemClickListener { _, _, _ ->

            }
        }
    }

    override fun successList(list: List<BindCard>) {
        mList = list as ArrayList<BindCard>
        bindCardAdapter.setNewData(mList)
    }

    override fun failStr(string: String) {
        toast(string)
    }

    override fun showLoading() {
        showProgress()
    }

    override fun dismissLoading() {
        hideProgress(smarFresh)
    }

    private var firstime: Long = 0
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            val secondtime = System.currentTimeMillis()
            if (secondtime - firstime > 3000) {
                toast("再按一次返回键退出")
                firstime = System.currentTimeMillis()
                return true
            } else {
                System.exit(0)
            }
        }
        return super.onKeyDown(keyCode, event)
    }
}