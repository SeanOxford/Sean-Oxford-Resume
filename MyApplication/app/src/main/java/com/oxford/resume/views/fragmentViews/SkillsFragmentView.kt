package com.oxford.resume.views.fragmentViews

import android.content.Context
import com.oxford.resume.R

public class SkillsFragmentView(context: Context?, callback: InfoFragmentViewCallback) :
    AbsInfoFragmentView(context, callback) {





    override fun getLayout(): Int {
        return R.layout.layout_skills_fragment
    }


}