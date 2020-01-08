package com.example.myapplication.views

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.widget.ImageView
import com.example.myapplication.R


class ScreenFractionImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : ImageView(context, attrs, defStyle) {


    private var mScreenFraction: Int? = null


    init {
        if (attrs != null) {
            val attributes = context.obtainStyledAttributes(
                attrs,
                R.styleable.ScreenFractionImageViewAttrs
            )
            mScreenFraction = attributes
                .getInteger(R.styleable.ScreenFractionImageViewAttrs_screenFraction, 1)

            Log.d("nnn", String.format("screenFraction: %d", mScreenFraction))
        }


    }
}