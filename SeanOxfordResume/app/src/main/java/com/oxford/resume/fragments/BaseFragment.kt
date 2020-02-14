package com.oxford.resume.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.oxford.resume.ResumeApp
import com.oxford.resume.views.fragmentViews.BaseFragmentView
import com.squareup.otto.Bus
import javax.inject.Inject

abstract class BaseFragment : Fragment() {

    @Inject
    protected lateinit var mBus: Bus


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.application as ResumeApp).myComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return getMainView()
    }


    override fun onStart() {
        super.onStart()
        mBus.register(this)
    }

    override fun onStop() {
        super.onStop()
        mBus.unregister(this)
    }


    abstract fun getMainView(): BaseFragmentView


}