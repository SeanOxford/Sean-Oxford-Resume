package com.seanoxford.resume.fragments

import com.seanoxford.resume.views.fragmentViews.BaseFragmentView
import com.seanoxford.resume.views.fragmentViews.FibonacciFragmentView

class FibonacciFragment : AbsInfoFragment() {

    override fun getMainView(): BaseFragmentView {
        return FibonacciFragmentView(context, this)
    }


}