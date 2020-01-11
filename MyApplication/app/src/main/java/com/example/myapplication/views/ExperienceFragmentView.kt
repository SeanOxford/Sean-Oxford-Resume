package com.example.myapplication.views

import android.content.Context
import com.example.myapplication.R
import com.example.myapplication.util.TextUtil
import kotlinx.android.synthetic.main.layout_experience_fragment.view.*

class ExperienceFragmentView(context: Context?, callback: InfoFragmentViewCallback) :
    AbsInfoFragmentView(context, callback) {
    override fun animateIn() {
    }

    override fun getLayout(): Int {
        return R.layout.layout_experience_fragment
    }

    init {
        TextView_experience_fragment_list.text = TextUtil.formatBullets(
            context,
            resources.getString(R.string.experience_fragment_sincerely_list))

        TextView_experience_fragment_ms_list.text = TextUtil.formatBullets(
            context,
            resources.getString(R.string.experience_fragment_ms_list))
    }


}