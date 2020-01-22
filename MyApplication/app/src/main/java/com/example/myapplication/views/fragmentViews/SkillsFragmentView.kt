package com.example.myapplication.views.fragmentViews

import android.content.Context
import com.example.myapplication.R
import com.example.myapplication.views.AbsInfoFragmentView
import kotlinx.android.synthetic.main.layout_skills_fragment.view.*

public class SkillsFragmentView(context: Context?, callback: InfoFragmentViewCallback) :
    AbsInfoFragmentView(context, callback) {





    override fun getLayout(): Int {
        return R.layout.layout_skills_fragment
    }


}