package com.seanoxford.resume.fragments

import com.seanoxford.resume.util.OttoBusClasses
import com.seanoxford.resume.views.fragmentViews.AbsInfoFragmentView
import com.seanoxford.resume.views.fragmentViews.ExperienceFragmentView

class ExperienceFragment : AbsInfoFragment(), ExperienceFragmentView.ExperienceFragmentViewCallback{



    override fun getMainView(): AbsInfoFragmentView {
        return ExperienceFragmentView(context, this, this)
    }


    override fun onAppClicked(appId: Int) {
        mBus.post(OttoBusClasses.GoToAppStoreEvent(appId))
    }


}