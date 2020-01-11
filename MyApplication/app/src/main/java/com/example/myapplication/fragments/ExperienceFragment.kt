package com.example.myapplication.fragments

import com.example.myapplication.R
import com.example.myapplication.views.AbsInfoFragmentView
import com.example.myapplication.views.ExperienceFragmentView

class ExperienceFragment : AbsInfoFragment(){

    override fun getMainView(): AbsInfoFragmentView {
        return ExperienceFragmentView(context, this)
    }


}