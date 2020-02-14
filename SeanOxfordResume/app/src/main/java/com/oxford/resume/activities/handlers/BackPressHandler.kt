package com.oxford.resume.activities.handlers

import android.app.Application
import com.oxford.resume.ResumeApp
import com.oxford.resume.util.OttoBusClasses
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
        public val BEHAVIOR_EXERCISE_TO_MENU = 2

    }


    var mBackPressBehavior = 0


    init {
        (app as ResumeApp).myComponent.inject(this)
    }


    fun registerBus() {
        mBus.register(this)
    }

    fun unregisterBus() {
        mBus.unregister(this)
    }

    public fun isCustomBehavior(): Boolean {
        return mBackPressBehavior != BEHAVIOR_DEFAULT
    }

    fun executeCustomBehavior() {
        when (mBackPressBehavior) {
            BEHAVIOR_BACK_TO_MENU -> mBus.post(OttoBusClasses.InfoFragmentGoBackEvent())
            BEHAVIOR_EXERCISE_TO_MENU -> mBus.post(OttoBusClasses.BackPressFromExerciseMenuEvent())
        }
    }


    @Subscribe
    public fun onSetBackPressBehaviorEvent(e: OttoBusClasses.SetBackPressBehaviorEvent) {
        mBackPressBehavior = e.behavior
    }


}