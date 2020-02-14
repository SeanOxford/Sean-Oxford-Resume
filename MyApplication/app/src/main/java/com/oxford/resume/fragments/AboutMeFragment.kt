package com.oxford.resume.fragments

import com.oxford.resume.util.OttoBusClasses
import com.oxford.resume.views.fragmentViews.AboutMeFragmentView
import com.oxford.resume.views.fragmentViews.AbsInfoFragmentView

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