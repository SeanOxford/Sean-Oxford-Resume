package com.example.myapplication.views

import android.content.Context
import com.example.myapplication.R
import com.example.myapplication.util.TextUtil
import kotlinx.android.synthetic.main.layout_experience_fragment.view.*

class ExperienceFragmentView(
    context: Context?,
    baseCallback: InfoFragmentViewCallback,
    callback: ExperienceFragmentViewCallback
) :
    AbsInfoFragmentView(context, baseCallback) {

    companion object {
        const val POSTAGRAM_CLICK_ID = 0
        const val INK_CLICK_ID = 1
    }


    interface ExperienceFragmentViewCallback {
        fun onAppClicked(appId: Int)
    }

    val mExpCallback = callback


    init {
        TextView_experience_fragment_list.text = TextUtil.formatBullets(
            context,
            resources.getString(R.string.experience_fragment_sincerely_list)
        )

        TextView_experience_fragment_ms_list.text = TextUtil.formatBullets(
            context,
            resources.getString(R.string.experience_fragment_ms_list)
        )

        initSincerelyButtons()
    }


    private fun initSincerelyButtons() {
        ImageView_experience_fragment_ink.setOnClickListener{ mExpCallback.onAppClicked(INK_CLICK_ID)}
        ImageView_experience_fragment_postagram.setOnClickListener{ mExpCallback.onAppClicked(POSTAGRAM_CLICK_ID)}
    }


    override fun getLayout(): Int {
        return R.layout.layout_experience_fragment
    }


}