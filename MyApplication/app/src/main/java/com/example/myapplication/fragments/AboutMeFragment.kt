package com.example.myapplication.fragments

import android.view.View
import com.example.myapplication.util.OttoBusClasses
import com.example.myapplication.views.fragmentViews.AboutMeFragmentView
import com.example.myapplication.views.AbsInfoFragmentView
import kotlinx.android.synthetic.main.layout_about_me_fragment.*

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