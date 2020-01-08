package com.example.myapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.myapplication.MyApp
import com.squareup.otto.Bus
import javax.inject.Inject

abstract class BaseFragment : Fragment(){

    @Inject
    protected lateinit var mBus: Bus


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.application as MyApp).myComponent.inject(this)
    }


    override fun onStart() {
        super.onStart()
        mBus.register(this)
    }

    override fun onStop() {
        super.onStop()
        mBus.unregister(this)
    }













}