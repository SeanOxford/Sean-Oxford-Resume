package com.oxford.resume.fragments

import com.oxford.resume.activities.handlers.BackPressHandler
import com.oxford.resume.util.OttoBusClasses
import com.oxford.resume.views.fragmentViews.BaseFragmentView
import com.oxford.resume.views.fragmentViews.LinkedInFragmentView
import com.squareup.otto.Subscribe

public class LinkedInFragment : BaseFragment() {


    override fun getMainView(): BaseFragmentView {
        return LinkedInFragmentView(context)
    }

    override fun onStart() {
        super.onStart()
        mBus.post(OttoBusClasses.SetBackPressBehaviorEvent(BackPressHandler.BEHAVIOR_BACK_TO_MENU))

    }


    @Subscribe
    public fun onInfoFragmentGoBackEvent(e: OttoBusClasses.InfoFragmentGoBackEvent) {
        fragmentManager?.popBackStack()
    }

}