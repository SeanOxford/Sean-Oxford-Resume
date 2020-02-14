package com.oxford.resume.views.fragmentViews

import android.content.Context
import com.oxford.resume.R
import kotlinx.android.synthetic.main.layout_about_me_fragment.view.*

public class AboutMeFragmentView(
    context: Context?,
    baseCallback: InfoFragmentViewCallback,
    callback: AboutMeFragmentViewCallback
) :
    AbsInfoFragmentView(context, baseCallback) {

    private val mAboutMeCallback = callback


    interface AboutMeFragmentViewCallback {
        fun onLinkedInClicked()
        fun onEmailClicked()
        fun onPhoneNumberClicked()
    }


    init {
        ImageView_about_me_linkedin.setOnClickListener {
            mAboutMeCallback.onLinkedInClicked()
        }

        TextView_about_me_email.setOnClickListener {
            mAboutMeCallback.onEmailClicked()
        }

        TextView_about_me_phone_number.setOnClickListener { mAboutMeCallback.onPhoneNumberClicked() }


    }

    override fun getLayout(): Int {
        return R.layout.layout_about_me_fragment
    }


}