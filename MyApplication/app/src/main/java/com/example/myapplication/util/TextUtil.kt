package com.example.myapplication.util

import android.content.Context
import android.text.Spanned
import android.text.style.BulletSpan
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.util.Log


class TextUtil{


    companion object {


        //https://stackoverflow.com/questions/40990755/add-bullets-with-proper-formatting-in-android
        fun formatBullets(context: Context?, text: String) : SpannableStringBuilder{
            val arr = text.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()


            val bulletGap = AppUtil.dptoPx(context, 10).toInt()
//            val bulletGap = AppUtil.dptoPx(context, 10).toInt() - 10

            val ssb = SpannableStringBuilder()
            for (i in arr.indices) {
                val line = arr[i]
                val ss = SpannableString(line)
                ss.setSpan(BulletSpan(bulletGap), 0, line.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                ssb.append(ss)

                //avoid last "\n"
                if (i + 1 < arr.size)
                    ssb.append("\n")

            }

            return ssb
        }
    }









}