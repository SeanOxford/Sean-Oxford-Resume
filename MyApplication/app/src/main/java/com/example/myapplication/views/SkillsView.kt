package com.example.myapplication.views

import android.content.Context
import com.example.myapplication.R

public class SkillsView(context: Context?, callback: AbsInfoFragmentView.InfoFragmentViewCallback) :
    AbsInfoFragmentView(context, callback) {


    override fun getLayout(): Int {
        return R.layout.layout_skills_fragment
    }


}