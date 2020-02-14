package com.oxford.resume.activities.anim

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View
import androidx.core.animation.addListener

class GoToFragmentAnimation(
    val recyclerView: View,
    val portrait: View,
    val name: View,
    val title: View,
    val experience: View,
    val callback: Callback
) {


    interface Callback {
        fun onDropMenuAnimFinish()
    }

    public fun startAnimation() {
        dropMenu()
        fadeOutHeader()
    }


    private fun dropMenu() {
        val menuDropAnim = ObjectAnimator.ofFloat(recyclerView, View.TRANSLATION_Y, recyclerView.height.toFloat())
        menuDropAnim.duration = 400
        menuDropAnim.addListener({ callback.onDropMenuAnimFinish() })
        menuDropAnim.start()
    }


    private fun fadeOutHeader() {
        val animatorSet = AnimatorSet()


        val portraitFadeAnim = ObjectAnimator.ofFloat(portrait, View.ALPHA, 0f)
        val titleFadeAnim = ObjectAnimator.ofFloat(name, View.ALPHA, 0f)
        val subtitleFadeAnim = ObjectAnimator.ofFloat(title, View.ALPHA, 0f)
        val experienceFadeAnim = ObjectAnimator.ofFloat(experience, View.ALPHA, 0f)

        animatorSet.playTogether(
            portraitFadeAnim,
            titleFadeAnim,
            subtitleFadeAnim,
            experienceFadeAnim
        )

        animatorSet.duration = 100

        animatorSet.start()
    }


}