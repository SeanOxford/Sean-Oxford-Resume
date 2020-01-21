package com.example.myapplication.fragments

import com.example.myapplication.views.AboutMeView
import com.example.myapplication.views.AbsInfoFragmentView

public class AboutMeFragment : AbsInfoFragment() {

    override fun getMainView(): AbsInfoFragmentView {
        return AboutMeView(context, this)
    }

}