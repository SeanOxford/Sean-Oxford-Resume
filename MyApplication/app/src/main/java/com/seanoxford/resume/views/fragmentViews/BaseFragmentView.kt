package com.seanoxford.resume.views.fragmentViews

import android.content.Context
import android.view.View
import android.widget.RelativeLayout

public abstract class BaseFragmentView(context: Context?) : RelativeLayout(context){


    abstract fun getLayout() : Int

    init {
        View.inflate(context, getLayout(), this)
    }




}