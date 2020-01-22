package com.example.myapplication.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.MyApp
import com.example.myapplication.activities.handlers.BackPressHandler
import com.example.myapplication.util.OttoBusClasses
import com.example.myapplication.views.AbsInfoFragmentView
import com.example.myapplication.views.fragmentViews.BaseFragmentView
import com.squareup.otto.Bus
import com.squareup.otto.Subscribe
import javax.inject.Inject

abstract class BaseFragment : Fragment() {

    @Inject
    protected lateinit var mBus: Bus




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.application as MyApp).myComponent.inject(this)
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