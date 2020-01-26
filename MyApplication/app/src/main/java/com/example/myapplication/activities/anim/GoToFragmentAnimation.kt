package com.example.myapplication.activities.anim

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View
import androidx.core.animation.addListener
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_menu_fragment.view.*

class GoToFragmentAnimation(val recyclerView : View, val portrait: View, val name: View, val title: View,  val callback : Callback) {


    interface Callback{
        fun onDropMenuAnimFinish()
    }

    public fun startAnimation() {
        dropMenu()
        fadeOutHeader()
    }



    private fun dropMenu() {
        val menuDropAnim = ObjectAnimator.ofFloat(recyclerView, "translationY", recyclerView.height.toFloat())
        menuDropAnim.duration = 400
        menuDropAnim.addListener({ callback.onDropMenuAnimFinish() })
        menuDropAnim.start()
    }


    private fun fadeOutHeader() {
        val animatorSet = AnimatorSet()


        val portraitFadeAnim = ObjectAnimator.ofFloat(portrait, "alpha", 0f)
        val titleFadeAnim = ObjectAnimator.ofFloat(name, "alpha", 0f)
        val subtitleFadeAnim = ObjectAnimator.ofFloat(title, "alpha", 0f)

        animatorSet.playTogether(
            portraitFadeAnim,
            titleFadeAnim,
            subtitleFadeAnim
        )

        animatorSet.duration = 100

        animatorSet.start()
    }



}