package com.example.myapplication.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.MyApp
import com.example.myapplication.activities.handlers.BackPressHandler
import com.example.myapplication.util.OttoBusClasses
import com.squareup.otto.Bus
import com.squareup.otto.Subscribe
import javax.inject.Inject

public abstract class BaseActivity : AppCompatActivity() {


    @Inject
    protected lateinit var mBus: Bus

    private var mBackPressHandler: BackPressHandler? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as MyApp).myComponent.inject(this)
        mBackPressHandler = BackPressHandler(application)

    }

    override fun onStart() {
        super.onStart()
        mBus.register(this)
        mBackPressHandler?.registerBus()

    }

    override fun onStop() {
        super.onStop()
        mBus.unregister(this)
        mBackPressHandler?.unregisterBus()
    }


    override fun onBackPressed() {
        if(mBackPressHandler!!.isCustomBehavior()){
            mBackPressHandler?.executeCustomBehavior()
        } else {
            super.onBackPressed()
        }

    }






}