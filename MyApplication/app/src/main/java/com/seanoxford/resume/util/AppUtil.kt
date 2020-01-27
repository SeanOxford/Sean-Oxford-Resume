package com.seanoxford.resume.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import com.seanoxford.resume.R

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
            activity?.startActivity(emailIntent)
        }
    }
}