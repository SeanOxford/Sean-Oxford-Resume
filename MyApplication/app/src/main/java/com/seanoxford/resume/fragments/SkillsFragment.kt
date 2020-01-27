package com.seanoxford.resume.fragments

import com.seanoxford.resume.views.fragmentViews.AbsInfoFragmentView
import com.seanoxford.resume.views.fragmentViews.SkillsFragmentView

class SkillsFragment : AbsInfoFragment() {


    override fun getMainView(): AbsInfoFragmentView {
        return SkillsFragmentView(context, this)
    }





}