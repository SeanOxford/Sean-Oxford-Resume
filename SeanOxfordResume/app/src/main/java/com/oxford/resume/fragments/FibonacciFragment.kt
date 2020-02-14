package com.oxford.resume.fragments

import com.oxford.resume.views.fragmentViews.BaseFragmentView
import com.oxford.resume.views.fragmentViews.FibonacciFragmentView

class FibonacciFragment : AbsInfoFragment() {

    override fun getMainView(): BaseFragmentView {
        return FibonacciFragmentView(context, this)
    }


}