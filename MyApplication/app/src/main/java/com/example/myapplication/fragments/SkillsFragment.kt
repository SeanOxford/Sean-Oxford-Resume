package com.example.myapplication.fragments

import com.example.myapplication.views.AbsInfoFragmentView
import com.example.myapplication.views.fragmentViews.SkillsFragmentView

class SkillsFragment : AbsInfoFragment() {


    override fun getMainView(): AbsInfoFragmentView {
        return SkillsFragmentView(context, this)
    }





}