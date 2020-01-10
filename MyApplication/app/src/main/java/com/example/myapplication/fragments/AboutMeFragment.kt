package com.example.myapplication.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.activities.handlers.BackPressHandler
import com.example.myapplication.util.OttoBusClasses
import com.example.myapplication.views.AboutMeView
import com.squareup.otto.Subscribe

public class AboutMeFragment : BaseFragment(), AboutMeView.AboutMeViewCallback {


    private  var mView: AboutMeView? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = AboutMeView(context, this)
        initViews(mView)

        return mView
    }

    override fun onStart() {
        super.onStart()
        mBus.post(OttoBusClasses.SetBackPressBehaviorEvent(BackPressHandler.BEHAVIOR_BACK_TO_MENU))
    }


    fun initViews(view: View?) {

    }


    override fun anythingClicked() {
        mBus.post(OttoBusClasses.SetBackPressBehaviorEvent(BackPressHandler.BEHAVIOR_BACK_TO_MENU))
    }

    override fun animateOutComplete() {
        mBus.post(OttoBusClasses.SetBackPressBehaviorEvent(BackPressHandler.BEHAVIOR_DEFAULT))
        fragmentManager?.popBackStack()
    }


    @Subscribe
    public fun onAboutMeFragmentGoBackEvent(e: OttoBusClasses.AboutMeFragmentGoBackEvent) {
        mView?.animateOut()

    }


}