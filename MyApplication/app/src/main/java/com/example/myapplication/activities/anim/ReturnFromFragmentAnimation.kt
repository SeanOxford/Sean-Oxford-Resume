package com.example.myapplication.activities.anim

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View
import androidx.core.animation.addListener
import kotlinx.android.synthetic.main.layout_menu_fragment.view.*

class ReturnFromFragmentAnimation(val recyclerView : View, val portrait: View, val name: View, val title: View){



    public fun startAnimation() {
        raiseMenu()
        fadeHeaderIn()
    }


    public fun raiseMenu() {
        val menuDropAnim = ObjectAnimator.ofFloat(recyclerView, "translationY", 0f)
        menuDropAnim.duration = 400
        menuDropAnim.start()
    }

    public fun fadeHeaderIn() {
        val portraitRaiseAnim = ObjectAnimator.ofFloat(portrait, "translationY", 0f)
        val portraitFadeAnim = ObjectAnimator.ofFloat(portrait, "alpha", 1f)

        val titleRaiseAnim = ObjectAnimator.ofFloat(name, "translationY", 0f)
        val titleFadeAnim = ObjectAnimator.ofFloat(name, "alpha", 1f)

        val subtitleRaiseAnim = ObjectAnimator.ofFloat(title, "translationY", 0f)
        val subtitleFadeAnim = ObjectAnimator.ofFloat(title, "alpha", 1f)

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