package com.oxford.resume.fragments

import com.oxford.resume.util.OttoBusClasses
import com.oxford.resume.views.fragmentViews.AbsInfoFragmentView
import com.oxford.resume.views.fragmentViews.ExperienceFragmentView

class ExperienceFragment : AbsInfoFragment(), ExperienceFragmentView.ExperienceFragmentViewCallback{



    override fun getMainView(): AbsInfoFragmentView {
        return ExperienceFragmentView(context, this, this)
    }


    override fun onAppClicked(appId: Int) {
        mBus.post(OttoBusClasses.GoToAppStoreEvent(appId))
    }


}