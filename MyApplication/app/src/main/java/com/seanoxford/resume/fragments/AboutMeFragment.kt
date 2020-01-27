package com.seanoxford.resume.fragments

import com.seanoxford.resume.util.OttoBusClasses
import com.seanoxford.resume.views.fragmentViews.AboutMeFragmentView
import com.seanoxford.resume.views.fragmentViews.AbsInfoFragmentView

public class AboutMeFragment : AbsInfoFragment(), AboutMeFragmentView.AboutMeFragmentViewCallback {

    override fun onPhoneNumberClicked() {
        mBus.post(OttoBusClasses.SendTextMsgEvent())
    }

    override fun onEmailClicked() {
        mBus.post(OttoBusClasses.EmailClickedEvent())

    }

    override fun getMainView(): AbsInfoFragmentView {
        return AboutMeFragmentView(context, this, this)
    }

    override fun onLinkedInClicked() {
        mBus.post(OttoBusClasses.GoToLinkedInEvent())
    }


}