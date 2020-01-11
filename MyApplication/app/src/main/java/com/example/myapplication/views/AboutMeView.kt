package com.example.myapplication.views

import android.animation.ObjectAnimator
import android.content.Context
import androidx.core.view.get
import androidx.core.view.size
import com.example.myapplication.R
import kotlinx.android.synthetic.main.layout_about_me_fragment.view.*

public class AboutMeView(context: Context?, callback: InfoFragmentViewCallback) :
    AbsInfoFragmentView(context, callback) {
    override fun animateIn() {
        val fadeSpeed = 500L

        val bgFadeIn = ObjectAnimator.ofFloat(FrameLayout_about_me_bg, "alpha", 0f, 1f)
        bgFadeIn.duration = fadeSpeed
        bgFadeIn.start()

        val headerFadeIn = ObjectAnimator.ofFloat(RelativeLayout_about_me_title_container, "alpha", 0f, 1f)
        headerFadeIn.duration = fadeSpeed
        headerFadeIn.start()


        val fadeCascadeCoefficient = 100L
        for (i in 0 until LinearLayout_about_me_info.size){
            val startDelay = i.toLong() * fadeCascadeCoefficient + 200L
            val viewFadeIn = ObjectAnimator.ofFloat(LinearLayout_about_me_info[i], "alpha",  1f)
            viewFadeIn.duration = fadeSpeed
            viewFadeIn.startDelay = startDelay
            viewFadeIn.start()
        }
    }

    override fun getLayout(): Int {
        return R.layout.layout_about_me_fragment
    }


}