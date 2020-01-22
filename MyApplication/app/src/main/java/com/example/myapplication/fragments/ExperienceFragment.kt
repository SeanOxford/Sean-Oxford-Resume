package com.example.myapplication.fragments

import android.view.View
import com.example.myapplication.util.OttoBusClasses
import com.example.myapplication.views.AbsInfoFragmentView
import com.example.myapplication.views.fragmentViews.ExperienceFragmentView

class ExperienceFragment : AbsInfoFragment(), ExperienceFragmentView.ExperienceFragmentViewCallback{



    override fun getMainView(): AbsInfoFragmentView {
        return ExperienceFragmentView(context, this, this)
    }


    override fun onAppClicked(appId: Int) {
        mBus.post(OttoBusClasses.GoToAppStoreEvent(appId))
    }


}