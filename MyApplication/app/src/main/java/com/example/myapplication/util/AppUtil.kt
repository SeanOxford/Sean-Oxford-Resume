package com.example.myapplication.util

import android.app.Application
import android.content.Context
import javax.annotation.Resource

class AppUtil{

    companion object {
        public fun dptoPx(context: Context?, dp: Int) : Float{
            return dp * context?.resources?.displayMetrics?.density!!
        }
    }
}