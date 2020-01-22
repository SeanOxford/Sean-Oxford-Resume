package com.example.myapplication.fragments

import android.util.Log
import com.example.myapplication.util.OttoBusClasses
import com.example.myapplication.views.AbsInfoFragmentView
import com.example.myapplication.views.fragmentViews.BaseFragmentView
import com.example.myapplication.views.fragmentViews.LinkedInFragmentView
import com.squareup.otto.Subscribe

public class LinkedInFragment : BaseFragment() {


    override fun getMainView(): BaseFragmentView {
        return LinkedInFragmentView(context)
    }




    @Subscribe
    public fun onInfoFragmentGoBackEvent(e: OttoBusClasses.InfoFragmentGoBackEvent) {
        Log.d("nnn", String.format("got??"))
        fragmentManager?.popBackStack()
    }

}