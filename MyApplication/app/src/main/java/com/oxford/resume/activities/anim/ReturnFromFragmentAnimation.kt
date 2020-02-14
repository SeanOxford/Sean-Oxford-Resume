package com.oxford.resume.activities.anim

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View

class ReturnFromFragmentAnimation(val recyclerView : View, val portrait: View, val name: View, val title: View){



    public fun startAnimation() {
        raiseMenu()
        fadeHeaderIn()
    }


    public fun raiseMenu() {
        val menuDropAnim = ObjectAnimator.ofFloat(recyclerView, View.TRANSLATION_Y, 0f)
        menuDropAnim.duration = 400
        menuDropAnim.start()
    }

    public fun fadeHeaderIn() {
        val portraitRaiseAnim = ObjectAnimator.ofFloat(portrait, View.TRANSLATION_Y, 0f)
        val portraitFadeAnim = ObjectAnimator.ofFloat(portrait, View.ALPHA, 1f)

        val titleRaiseAnim = ObjectAnimator.ofFloat(name, View.TRANSLATION_Y, 0f)
        val titleFadeAnim = ObjectAnimator.ofFloat(name, View.ALPHA, 1f)

        val subtitleRaiseAnim = ObjectAnimator.ofFloat(title, View.TRANSLATION_Y, 0f)
        val subtitleFadeAnim = ObjectAnimator.ofFloat(title, View.ALPHA, 1f)

        val animatorSet = AnimatorSet()
        animatorSet.playTogether(
            portraitRaiseAnim,
            portraitFadeAnim,
            titleRaiseAnim,
            titleFadeAnim,
            subtitleRaiseAnim,
            subtitleFadeAnim
        )

        animatorSet.duration = 400
        animatorSet.start()
    }


}