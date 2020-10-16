package com.monebac.com.jetpack

import android.view.View
import com.monebac.com.R
import com.monebac.com.base.BaseActivity
import com.monebac.com.jetpack.lifecycle.LifecyActivity
import com.monebac.com.jetpack.viewmodel.ViewModelActivity
import kotlinx.android.synthetic.main.act_jetpack.*
import kotlinx.android.synthetic.main.layout_title.*
import org.jetbrains.anko.startActivity

class MainJetpackActivity : BaseActivity() {
    override fun initLayout(): Int {
        return R.layout.act_jetpack
    }

    override fun initData() {
        back.visibility = View.GONE
        tv_title.text = "Jet"
        bt_ViewModel.setOnClickListener {
            startActivity<ViewModelActivity>()
        }
        bt_lifecycle.setOnClickListener {
            startActivity<LifecyActivity>()
        }
    }
}