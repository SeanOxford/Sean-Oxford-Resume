package com.oxford.resume.views.fragmentViews

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import androidx.core.animation.addListener
import androidx.core.view.get
import androidx.core.view.size
import kotlinx.android.synthetic.main.layout_about_me_fragment.view.*

abstract class AbsInfoFragmentView(context: Context?, callback: InfoFragmentViewCallback) :
    BaseFragmentView(context) {

    interface InfoFragmentViewCallback {
        fun animateOutComplete()
    }

    private val mCallback = callback

    init {
        animateIn()
    }


    open protected fun animateIn() {
        val fadeSpeed = 500L

        val bgFadeIn = ObjectAnimator.ofFloat(FrameLayout_info_fragment_bg, "alpha", 0f, 1f)
        bgFadeIn.duration = fadeSpeed
        bgFadeIn.start()

        val fadeCascadeCoefficient = 100L
        for (i in 0 until LinearLayout_info_fragment_container.size) {
            val startDelay = i.toLong() * fadeCascadeCoefficient + 200L
            val viewFadeIn = ObjectAnimator.ofFloat(LinearLayout_info_fragment_container[i], "alpha", 1f)
            viewFadeIn.duration = fadeSpeed
            viewFadeIn.startDelay = startDelay
            viewFadeIn.start()
        }

    }


    public fun animateOut() {
        val animatorSet = AnimatorSet()
        val textFadeOut = ObjectAnimator.ofFloat(LinearLayout_info_fragment_container, "alpha", 1f, 0f)
        val bgFadeOut = ObjectAnimator.ofFloat(FrameLayout_info_fragment_bg, "alpha", 1f, 0f)

        animatorSet.playTogether(textFadeOut, bgFadeOut)

        animatorSet.duration = 500
        animatorSet.addListener({
            mCallback.animateOutComplete()
        })
        animatorSet.start()
    }


}