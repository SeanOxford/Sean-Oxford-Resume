package com.example.myapplication.util

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import com.example.myapplication.R
import javax.annotation.Resource

class AppUtil{

    companion object {
        public fun dptoPx(context: Context?, dp: Int) : Float{
            return dp * context?.resources?.displayMetrics?.density!!
        }

        public fun sendEmail(activity: Activity?){

            val emailIntent = Intent(
                Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto", activity?.resources?.getString(R.string.email_address), null
                )
            )
            Log.d("nnn", String.format("butts?"))
            activity?.startActivity(emailIntent)
        }
    }
}