package com.seanoxford.resume.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.seanoxford.resume.activities.handlers.BackPressHandler
import com.seanoxford.resume.util.OttoBusClasses
import com.seanoxford.resume.views.fragmentViews.AbsInfoFragmentView
import com.squareup.otto.Subscribe

abstract class AbsInfoFragment : BaseFragment(), AbsInfoFragmentView.InfoFragmentViewCallback {

    private var mView: AbsInfoFragmentView? = null
    private val mOttoBusHolder = InfoFragmentOttoBusHolder()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = getMainView() as AbsInfoFragmentView
        return mView
    }

    override fun onStart() {
        super.onStart()
        mBus.register(mOttoBusHolder)
        mBus.post(OttoBusClasses.SetBackPressBehaviorEvent(BackPressHandler.BEHAVIOR_BACK_TO_MENU))
    }

    override fun onStop() {
        super.onStop()
        mBus.unregister(mOttoBusHolder)
    }

    override fun animateOutComplete() {
        mBus.post(OttoBusClasses.SetBackPressBehaviorEvent(BackPressHandler.BEHAVIOR_DEFAULT))
        fragmentManager?.popBackStack()
    }


    inner class InfoFragmentOttoBusHolder{
        @Subscribe
        public fun onInfoFragmentGoBackEvent(e: OttoBusClasses.InfoFragmentGoBackEvent) {
            mView?.animateOut()
        }
    }


}