package com.example.myapplication.fragments

import com.example.myapplication.views.AbsInfoFragmentView
import com.example.myapplication.views.ExperienceFragmentView
import com.example.myapplication.views.SkillsView

class SkillsFragment : AbsInfoFragment() {


    override fun getMainView(): AbsInfoFragmentView {
        return SkillsView(context, this)
    }





}