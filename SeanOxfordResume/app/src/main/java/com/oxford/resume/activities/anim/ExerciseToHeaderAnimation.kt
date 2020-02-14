package com.oxford.resume.activities.anim

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View

class ExerciseToHeaderAnimation(val portrait: View, val name: View, val title: View, val exercise: View) {


    public fun startAnimation() {
        fadeOutExerciseTitle()
    }


    private fun raiseHeader() {
        val animSet = AnimatorSet()

        val distance = 0f

        val portraitRaiseAnim = ObjectAnimator.ofFloat(portrait, View.TRANSLATION_Y, distance)

        val titleRaiseAnim = ObjectAnimator.ofFloat(name, View.TRANSLATION_Y, distance)

        val subtitleRaiseAnim = ObjectAnimator.ofFloat(title, View.TRANSLATION_Y, distance)

        animSet.playTogether(portraitRaiseAnim, titleRaiseAnim, subtitleRaiseAnim)
        animSet.duration = 300

        animSet.start()
    }


    private fun fadeHeaderIn() {
        val portraitFadeAnim = ObjectAnimator.ofFloat(portrait, View.ALPHA, 1f)

        val titleFadeAnim = ObjectAnimator.ofFloat(name, View.ALPHA, 1f)

        val subtitleFadeAnim = ObjectAnimator.ofFloat(title, View.ALPHA, 1f)

        val animatorSet = AnimatorSet()
        animatorSet.playTogether(
            portraitFadeAnim,
            titleFadeAnim,
            subtitleFadeAnim
        )

        animatorSet.duration = 400
        animatorSet.start()
    }

    private fun fadeOutExerciseTitle() {
        val portraitFadeAnim = ObjectAnimator.ofFloat(exercise, View.ALPHA, 0f)
        portraitFadeAnim.duration = 200


        portraitFadeAnim.addListener(object : Animator.AnimatorListener{
            override fun onAnimationRepeat(animation: Animator?) {
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationStart(animation: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator?) {
                raiseHeader()
                fadeHeaderIn()
            }


        })

        portraitFadeAnim.start()
    }


}