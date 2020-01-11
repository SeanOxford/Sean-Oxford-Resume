package com.example.myapplication.views

import android.content.Context
import com.example.myapplication.R

class ExperienceFragmentView(context: Context?, callback: InfoFragmentViewCallback) : AbsInfoFragmentView(context, callback) {
    override fun animateIn() {
    }

    override fun getLayout(): Int {
        return R.layout.layout_about_me_fragment
    }


}