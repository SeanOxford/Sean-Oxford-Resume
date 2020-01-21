package com.example.myapplication.fragments

import com.example.myapplication.R
import com.example.myapplication.util.OttoBusClasses
import com.example.myapplication.views.AbsInfoFragmentView
import com.example.myapplication.views.ExperienceFragmentView

class ExperienceFragment : AbsInfoFragment(), ExperienceFragmentView.ExperienceFragmentViewCallback{



    override fun getMainView(): AbsInfoFragmentView {
        return ExperienceFragmentView(context, this, this)
    }


    override fun onAppClicked(appId: Int) {
        mBus.post(OttoBusClasses.GoToAppStoreEvent(appId))
    }


}