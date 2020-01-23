package com.example.myapplication.views

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import com.example.myapplication.R
import kotlinx.android.synthetic.main.view_menu_imageview.view.*


class ScreenFractionImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {


    private var mScreenFraction: Int? = null


    init {
        if (attrs != null) {
            val attributes = context.obtainStyledAttributes(
                attrs,
                R.styleable.ScreenFractionImageViewAttrs
            )
            mScreenFraction = attributes
                .getInteger(R.styleable.ScreenFractionImageViewAttrs_screenFraction, 1)
        }

        View.inflate(context, R.layout.view_menu_imageview, this)

    }

    public fun fadeToBlue(){
        val animSet = AnimatorSet();

        val fadeOutGreenAnim = ObjectAnimator.ofFloat(ImageView_menu_bar_green, View.ALPHA, 0f)
        val fadeOutBlueAnim = ObjectAnimator.ofFloat(ImageView_menu_bar_green, View.ALPHA, 1f)


        animSet.playTogether(fadeOutBlueAnim, fadeOutGreenAnim)
        animSet.duration = 500
        animSet.start()
    }


    public fun fadeToGreen(){
        val animSet = AnimatorSet()

        val fadeOutGreenAnim = ObjectAnimator.ofFloat(ImageView_menu_bar_green, View.ALPHA, 1f)
        val fadeOutBlueAnim = ObjectAnimator.ofFloat(ImageView_menu_bar_green, View.ALPHA, 0f)


        animSet.playTogether(fadeOutBlueAnim, fadeOutGreenAnim)
        animSet.duration = 500
        animSet.start()
    }







    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        layoutParams.height = resources.displayMetrics.heightPixels / mScreenFraction!!
    }



}