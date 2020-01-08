package com.example.myapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.myapplication.MyApp
import com.squareup.otto.Bus
import javax.inject.Inject

abstract class BaseFragment : Fragment(){




//    @Inject
//    public lateinit var mBus: mBus


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        (context?.applicationContext as MyApp).myComponent.inject(this)
    }









}