package com.oxford.resume.fragments

import com.oxford.resume.views.fragmentViews.AbsInfoFragmentView
import com.oxford.resume.views.fragmentViews.SkillsFragmentView

class SkillsFragment : AbsInfoFragment() {


    override fun getMainView(): AbsInfoFragmentView {
        return SkillsFragmentView(context, this)
    }





}