package com.seanoxford.resume.activities.anim

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View
import androidx.core.animation.addListener

class HeaderToExerciseAnimation(val portrait: View, val name: View, val title: View, val exercise: View){




    public fun startAnimation(){
        lowerHeader()
        fadeOutHeader()
    }



    private fun lowerHeader() {
        val animSet = AnimatorSet()

        val distance = 50f

        val portraitRaiseAnim = ObjectAnimator.ofFloat(portrait, View.TRANSLATION_Y, distance)

        val titleRaiseAnim = ObjectAnimator.ofFloat(name, View.TRANSLATION_Y, distance)

        val subtitleRaiseAnim = ObjectAnimator.ofFloat(title, View.TRANSLATION_Y, distance)

        animSet.playTogether(portraitRaiseAnim, titleRaiseAnim, subtitleRaiseAnim)
        animSet.duration = 300

        animSet.start()
    }


    private fun fadeOutHeader() {
        val portraitFadeAnim = ObjectAnimator.ofFloat(portrait, View.ALPHA, 0f)
        val titleFadeAnim = ObjectAnimator.ofFloat(name, View.ALPHA, 0f)
        val subtitleFadeAnim = ObjectAnimator.ofFloat(title, View.ALPHA, 0f)

        val animatorSet = AnimatorSet()
        animatorSet.playTogether(
            portraitFadeAnim,
            titleFadeAnim,
            subtitleFadeAnim
        )

        animatorSet.duration = 200

        animatorSet.addListener({
            fadeInExerciseTitle()
        })



        animatorSet.start()
    }


    private fun fadeInExerciseTitle(){
        val portraitFadeAnim = ObjectAnimator.ofFloat(exercise, View.ALPHA, 1f)
        portraitFadeAnim.duration = 400
        portraitFadeAnim.start()
    }










}