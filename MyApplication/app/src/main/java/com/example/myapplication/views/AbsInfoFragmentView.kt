package com.example.myapplication.views

import android.animation.ObjectAnimator
import android.content.Context
import android.view.View
import android.widget.RelativeLayout
import androidx.core.animation.addListener
import kotlinx.android.synthetic.main.layout_about_me_fragment.view.*

abstract class AbsInfoFragmentView(context: Context?, callback: InfoFragmentViewCallback) :
    RelativeLayout(context){

    interface InfoFragmentViewCallback {
        fun animateOutComplete()

    }

    private val mCallback = callback


    init {
        View.inflate(context, getLayout(), this)
        animateIn()
    }

    abstract fun getLayout() : Int

    abstract fun animateIn()


    public fun animateOut(){
        val viewFadeOut = ObjectAnimator.ofFloat(FrameLayout_about_me_bg, "alpha", 1f, 0f)
        viewFadeOut.duration = 500
        viewFadeOut.addListener({
            mCallback.animateOutComplete()})
        viewFadeOut.start()
    }





}