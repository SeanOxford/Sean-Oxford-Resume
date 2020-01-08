package com.example.myapplication.views

import android.content.Context
import android.content.res.Resources
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Animation
import android.view.animation.Transformation
import com.google.android.material.appbar.AppBarLayout


class ResizeableAppBarLayout : AppBarLayout {


    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    private val BASE_HEIGHT = 56
    private val SCREEN_THIRD = Resources.getSystem().displayMetrics.heightPixels / 3f





    public fun expandToThird() {
        startAnimation(AppBarExpandAnim(true))
    }

    public fun retractToBase(){
        startAnimation(AppBarExpandAnim(false))
    }


    inner class AppBarExpandAnim(expand: Boolean): Animation() {
        val expand = expand

        init {
            duration = 1000
            interpolator = AccelerateDecelerateInterpolator()
        }

        override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
            if(expand) {
                layoutParams.height = (BASE_HEIGHT + (interpolatedTime * (SCREEN_THIRD))).toInt()
            } else{
                layoutParams.height = (BASE_HEIGHT + ((1 - interpolatedTime) * (SCREEN_THIRD))).toInt()

            }
            layoutParams = layoutParams

        }
    }
}