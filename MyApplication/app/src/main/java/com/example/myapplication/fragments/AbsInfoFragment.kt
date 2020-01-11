package com.example.myapplication.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.activities.handlers.BackPressHandler
import com.example.myapplication.util.OttoBusClasses
import com.example.myapplication.views.AbsInfoFragmentView
import com.squareup.otto.Subscribe

abstract class AbsInfoFragment : BaseFragment(), AbsInfoFragmentView.InfoFragmentViewCallback {

    private var mView: AbsInfoFragmentView? = null
    private val mOttoBusHolder = InfoFragmentOttoBusHolder()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = getMainView()

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

    abstract fun getMainView() : AbsInfoFragmentView


    inner class InfoFragmentOttoBusHolder{
        @Subscribe
        public fun onAboutMeFragmentGoBackEvent(e: OttoBusClasses.InfoFragmentGoBackEvent) {
            mView?.animateOut()

        }
    }


}