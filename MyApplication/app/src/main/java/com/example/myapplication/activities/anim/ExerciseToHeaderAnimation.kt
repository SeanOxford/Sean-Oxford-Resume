package com.example.myapplication.activities.anim

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.util.Log
import android.view.View
import androidx.core.animation.addListener

class ExerciseToHeaderAnimation(val portrait: View, val name: View, val title: View, val exercise: View) {


    public fun startAnimation() {
        fadeOutExerciseTitle()
    }


    private fun raiseHeader() {
        val animSet = AnimatorSet()

        val distance = 0f

        val portraitRaiseAnim = ObjectAnimator.ofFloat(portrait, "translationY", distance)

        val titleRaiseAnim = ObjectAnimator.ofFloat(name, "translationY", distance)

        val subtitleRaiseAnim = ObjectAnimator.ofFloat(title, "translationY", distance)

        Log.d("nnn", String.format("2222b"))
        animSet.playTogether(portraitRaiseAnim, titleRaiseAnim, subtitleRaiseAnim)
        animSet.duration = 300

        animSet.start()
    }


    private fun fadeHeaderIn() {
        val portraitFadeAnim = ObjectAnimator.ofFloat(portrait, "alpha", 1f)

        val titleFadeAnim = ObjectAnimator.ofFloat(name, "alpha", 1f)

        val subtitleFadeAnim = ObjectAnimator.ofFloat(title, "alpha", 1f)

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
        val portraitFadeAnim = ObjectAnimator.ofFloat(exercise, "alpha", 0f)
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