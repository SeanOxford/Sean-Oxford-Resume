package com.example.myapplication.views

import android.animation.ObjectAnimator
import android.content.Context
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import com.example.myapplication.R
import kotlinx.android.synthetic.main.layout_toolbar_custom_view.view.*

public class ToolBarCustomView(context: Context) : FrameLayout(context) {





    init {
        inflate(context, R.layout.layout_toolbar_custom_view, this)
    }



    public fun setTitle(title: String){
        TextView_custom_toolbar_title.text = title
    }

    public fun fadeInTitle(){
        val fadeInAnim = ObjectAnimator.ofFloat(TextView_custom_toolbar_title, "alpha", 1f)
        fadeInAnim.startDelay = 200
        fadeInAnim.duration = 300
        fadeInAnim.start()
    }


    public fun fadeOutTitle(){
        val fadeInAnim = ObjectAnimator.ofFloat(TextView_custom_toolbar_title, "alpha", 0f)
        fadeInAnim.duration = 150
        fadeInAnim.start()
    }








}