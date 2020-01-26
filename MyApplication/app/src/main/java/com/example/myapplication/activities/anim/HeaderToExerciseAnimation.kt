package com.example.myapplication.activities.anim

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View
import androidx.core.animation.addListener
import kotlinx.android.synthetic.main.layout_menu_fragment.view.*

class HeaderToExerciseAnimation(val portrait: View, val name: View, val title: View, val exercise: View){




    public fun startAnimation(){
        lowerHeader()
        fadeOutHeader()
    }



    private fun lowerHeader() {
        val animSet = AnimatorSet()

        val distance = 50f

        val portraitRaiseAnim = ObjectAnimator.ofFloat(portrait, "translationY", distance)

        val titleRaiseAnim = ObjectAnimator.ofFloat(name, "translationY", distance)

        val subtitleRaiseAnim = ObjectAnimator.ofFloat(title, "translationY", distance)

        animSet.playTogether(portraitRaiseAnim, titleRaiseAnim, subtitleRaiseAnim)
        animSet.duration = 300

        animSet.start()
    }


    private fun fadeOutHeader() {
        val portraitFadeAnim = ObjectAnimator.ofFloat(portrait, "alpha", 0f)
        val titleFadeAnim = ObjectAnimator.ofFloat(name, "alpha", 0f)
        val subtitleFadeAnim = ObjectAnimator.ofFloat(title, "alpha", 0f)

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
        val portraitFadeAnim = ObjectAnimator.ofFloat(exercise, "alpha", 1f)
        portraitFadeAnim.duration = 400
        portraitFadeAnim.start()
    }










}