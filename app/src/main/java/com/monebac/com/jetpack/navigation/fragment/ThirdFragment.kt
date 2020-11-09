package com.monebac.com.jetpack.navigation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.monebac.com.R
import kotlinx.android.synthetic.main.first_fragment.view.*
import kotlinx.android.synthetic.main.layout_title.view.*

class ThirdFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val inflate = inflater.inflate(R.layout.third_fragment, container, false)
        initData(inflate)
        return inflate
    }


    fun initData(inflate: View) {
        inflate.run {
            tv_title.text = "Navigation"
            back.visibility = View.GONE
        }
    }
}