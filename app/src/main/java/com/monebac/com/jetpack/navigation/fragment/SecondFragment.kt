package com.monebac.com.jetpack.navigation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.monebac.com.R
import kotlinx.android.synthetic.main.layout_title.view.*
import kotlinx.android.synthetic.main.second_fragment.view.*

class SecondFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val inflate = inflater.inflate(R.layout.second_fragment, container, false)
        initData(inflate)
        return inflate
    }


    fun initData(inflate: View) {
        inflate.run {
            tv_title.text = "第二个"
            tv_cont.text = arguments?.getString("name")
            back.visibility = View.GONE
        }
    }
}