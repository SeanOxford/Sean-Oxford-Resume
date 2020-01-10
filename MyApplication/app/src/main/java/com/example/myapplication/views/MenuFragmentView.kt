package com.example.myapplication.views

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.RelativeLayout
import androidx.core.animation.addListener
import com.example.myapplication.R
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.layout_menu_fragment.view.*

class MenuFragmentView(context: Context?, menuItemList: ArrayList<MenuItem>, callback: MenuFragmentViewCallbacks) :
    RelativeLayout(context), MenuFragmentGridRecyclerView.GridViewCallback {

    interface MenuFragmentViewCallbacks {
        fun onMenuItemSelected(title: String)
        fun onLeaveAnimFinished()
    }


    val mMenuItemList = menuItemList
    val mCallback = callback
    lateinit var mRecyclerView: MenuFragmentGridRecyclerView


    init {
        View.inflate(context, R.layout.layout_menu_fragment, this)
        initGridView()
    }


    private fun initGridView() {
        mRecyclerView = MenuFragmentGridRecyclerView(
            context,
            mMenuItemList,
            this
        )

        FrameLayout_menu_fragment_grid_container.addView(mRecyclerView)
    }


    private fun animateOut() {
        val menuDropAnim = ObjectAnimator.ofFloat(mRecyclerView, "translationY", mRecyclerView.height.toFloat())
        menuDropAnim.duration = 400
        menuDropAnim.addListener({ mCallback.onLeaveAnimFinished() })
        menuDropAnim.start()

        val portraitRaiseAnim = ObjectAnimator.ofFloat(CirclePortraitView_menu_fragment, "translationY", -200f)
        val portraitFadeAnim = ObjectAnimator.ofFloat(CirclePortraitView_menu_fragment, "alpha", 0f)

        val titleRaiseAnim = ObjectAnimator.ofFloat(TextView_menu_fragment_title, "translationY", -200f)
        val titleFadeAnim = ObjectAnimator.ofFloat(TextView_menu_fragment_title, "alpha", 0f)

        val subtitleRaiseAnim = ObjectAnimator.ofFloat(TextView_menu_fragment_subtitle, "translationY", -200f)
        val subtitleFadeAnim = ObjectAnimator.ofFloat(TextView_menu_fragment_subtitle, "alpha", 0f)

        val animatorSet = AnimatorSet()
        animatorSet.playTogether(
            portraitRaiseAnim,
            portraitFadeAnim,
            titleRaiseAnim,
            titleFadeAnim,
            subtitleRaiseAnim,
            subtitleFadeAnim
        )

        animatorSet.duration = 200


        animatorSet.start()
    }

    public fun animateIn() {
        val menuDropAnim = ObjectAnimator.ofFloat(mRecyclerView, "translationY", 0f)
        menuDropAnim.duration = 400

        val portraitRaiseAnim = ObjectAnimator.ofFloat(CirclePortraitView_menu_fragment, "translationY", 0f)
        val portraitFadeAnim = ObjectAnimator.ofFloat(CirclePortraitView_menu_fragment, "alpha", 1f)

        val titleRaiseAnim = ObjectAnimator.ofFloat(TextView_menu_fragment_title, "translationY", 0f)
        val titleFadeAnim = ObjectAnimator.ofFloat(TextView_menu_fragment_title, "alpha", 1f)

        val subtitleRaiseAnim = ObjectAnimator.ofFloat(TextView_menu_fragment_subtitle, "translationY", 0f)
        val subtitleFadeAnim = ObjectAnimator.ofFloat(TextView_menu_fragment_subtitle, "alpha", 1f)

        val animatorSet = AnimatorSet()
        animatorSet.playTogether(
            portraitRaiseAnim,
            portraitFadeAnim,
            titleRaiseAnim,
            titleFadeAnim,
            subtitleRaiseAnim,
            subtitleFadeAnim, menuDropAnim
        )

        animatorSet.duration = 200
        animatorSet.start()
    }


    override fun onMenuItemSelected(title: String) {
        animateOut()
        mCallback.onMenuItemSelected(title)
    }


    data class MenuItem(val title: String, val imageRes: Int)


}