package com.monebac.com.jetpack.navigation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.monebac.com.R
import kotlinx.android.synthetic.main.frag1_detail.view.*
import kotlinx.android.synthetic.main.layout_title.view.*

class DetailTwoFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val inflate = inflater.inflate(R.layout.frag1_detail, container, false)
        initData(inflate)
        return inflate

    }

    fun initData(inflate: View) {
        inflate.run {
            back.setOnClickListener {
                Navigation.findNavController(requireView()).popBackStack()
            }
            tv_login.setOnClickListener {
                findNavController().navigate(R.id.action_detail1Fragment_pop_including_firstFragment2)
            }
        }
    }

}