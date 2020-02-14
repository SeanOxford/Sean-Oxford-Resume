package com.oxford.resume.activities.anim

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View

class ReturnFromExerciseFragmentAnimation(val recyclerView: View, val title: View) {


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
        val animatorSet = AnimatorSet()

        val subtitleRaiseAnim = ObjectAnimator.ofFloat(title, View.TRANSLATION_Y, 0f)
        val subtitleFadeAnim = ObjectAnimator.ofFloat(title, View.ALPHA, 1f)

        animatorSet.playTogether(
            subtitleRaiseAnim,
            subtitleFadeAnim
        )

        animatorSet.duration = 400
        animatorSet.start()
    }


}