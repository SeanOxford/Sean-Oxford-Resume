package com.example.myapplication.views

import android.animation.ObjectAnimator
import android.content.Context
import android.util.Log
import android.view.View
import android.widget.RelativeLayout
import androidx.core.animation.addListener
import androidx.core.view.get
import androidx.core.view.size
import com.example.myapplication.R
import kotlinx.android.synthetic.main.layout_about_me_fragment.view.*

public class AboutMeView(context: Context?, callback: AboutMeViewCallback) : RelativeLayout(context) {

    val mCallback = callback

    interface AboutMeViewCallback {
        fun anythingClicked()
        fun animateOutComplete()

    }

    init {
        View.inflate(context, R.layout.layout_about_me_fragment, this)
        LinearLayout_about_me_text_container.setOnClickListener {
            mCallback.anythingClicked()
        }
        animateIn()
    }


    private fun animateIn(){
        val fadeSpeed = 500L

        val bgFadeIn = ObjectAnimator.ofFloat(FrameLayout_about_me_bg, "alpha", 0f, 1f)
        bgFadeIn.duration = fadeSpeed
        bgFadeIn.start()

        val headerFadeIn = ObjectAnimator.ofFloat(RelativeLayout_about_me_title_container, "alpha", 0f, 1f)
        headerFadeIn.duration = fadeSpeed
        headerFadeIn.start()


        val fadeCascadeCoefficient = 100L
        for (i in 0 until LinearLayout_about_me_info.size){
            val startDelay = i.toLong() * fadeCascadeCoefficient + 200L
            val viewFadeIn = ObjectAnimator.ofFloat(LinearLayout_about_me_info[i], "alpha",  1f)
            viewFadeIn.duration = fadeSpeed
            viewFadeIn.startDelay = startDelay
            viewFadeIn.start()
        }
    }


    public fun animateOut(){
        val viewFadeOut = ObjectAnimator.ofFloat(FrameLayout_about_me_bg, "alpha", 1f, 0f)
        viewFadeOut.duration = 500
        viewFadeOut.addListener({
            mCallback.animateOutComplete()})
        viewFadeOut.start()



    }



}