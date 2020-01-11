package com.example.myapplication.activities.handlers

import android.app.Application
import com.example.myapplication.MyApp
import com.example.myapplication.util.OttoBusClasses
import com.squareup.otto.Bus
import com.squareup.otto.Subscribe
import javax.inject.Inject

class BackPressHandler(app: Application?) {


    @Inject
    protected lateinit var mBus: Bus

    companion object {
        public val BEHAVIOR_DISABLED = -1
        public val BEHAVIOR_DEFAULT = 0
        public val BEHAVIOR_BACK_TO_MENU = 1
    }




    var mBackPressBehavior = 0


    init {
        (app as MyApp).myComponent.inject(this)
    }


    fun registerBus() {
        mBus.register(this)
    }

    fun unregisterBus() {
        mBus.unregister(this)
    }

    fun isCustomBehavior() : Boolean{
        return mBackPressBehavior != BEHAVIOR_DEFAULT
    }

    fun executeCustomBehavior(){
        when (mBackPressBehavior){
            BEHAVIOR_BACK_TO_MENU -> mBus.post(OttoBusClasses.InfoFragmentGoBackEvent())
        }
    }


    @Subscribe
    public fun onSetBackPressBehaviorEvent(e: OttoBusClasses.SetBackPressBehaviorEvent) {
        mBackPressBehavior = e.behavior
    }


}