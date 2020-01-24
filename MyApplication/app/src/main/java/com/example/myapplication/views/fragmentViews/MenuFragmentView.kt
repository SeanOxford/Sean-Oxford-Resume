package com.example.myapplication.views.fragmentViews

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import androidx.core.animation.addListener
import com.example.myapplication.R
import com.example.myapplication.fragments.MenuFragment
import com.example.myapplication.views.MenuFragmentGridRecyclerView
import kotlinx.android.synthetic.main.layout_menu_fragment.view.*

class MenuFragmentView(context: Context?, menuItemList: ArrayList<MenuItem>, callback: MenuFragmentViewCallbacks) :
    BaseFragmentView(context), MenuFragmentGridRecyclerView.GridViewCallback {


    interface MenuFragmentViewCallbacks {
        fun onNonNewFragmentMenuItemSelected(title: String)
        fun onNewFragmentMenuItemSelected(title: String)
        fun onLeaveAnimFinished()
        fun onMenuChangeFadeOutFinished()
    }


    val mMenuItemList = menuItemList
    val mCallback = callback
    lateinit var mRecyclerView: MenuFragmentGridRecyclerView


    init {
        initGridView()
    }

    override fun getLayout(): Int {
        return R.layout.layout_menu_fragment
    }


    public fun setNewData(data: List<MenuItem>) {
        mRecyclerView.switchMenu(data)
    }

    public fun fadeOutMenuButtons() {
        mRecyclerView.fadeOutMenu()
        raiseHeader()

        val durationOrDelay = 200L
        fadeOutHeader(durationOrDelay)
        fadeInExerciseTitle(durationOrDelay)
    }


    private fun initGridView() {
        mRecyclerView = MenuFragmentGridRecyclerView(
            context,
            mMenuItemList,
            this
        )

        FrameLayout_menu_fragment_grid_container.addView(mRecyclerView)
    }

    private fun fadeInExerciseTitle(delay: Long){
        val portraitFadeAnim = ObjectAnimator.ofFloat(TextView_menu_fragment_header_exercises, "alpha", 1f)
        portraitFadeAnim.duration = 400
        portraitFadeAnim.startDelay = delay
        portraitFadeAnim.start()
    }

    private fun dropMenu() {
        val menuDropAnim = ObjectAnimator.ofFloat(mRecyclerView, "translationY", mRecyclerView.height.toFloat())
        menuDropAnim.duration = 400
        menuDropAnim.addListener({ mCallback.onLeaveAnimFinished() })
        menuDropAnim.start()
    }

    public fun raiseMenu() {
        val menuDropAnim = ObjectAnimator.ofFloat(mRecyclerView, "translationY", 0f)
        menuDropAnim.duration = 400
        menuDropAnim.start()
    }

    private fun raiseHeader() {
        val animSet = AnimatorSet()

        val distance = -50f

        val portraitRaiseAnim = ObjectAnimator.ofFloat(CirclePortraitView_menu_fragment, "translationY", -distance)

        val titleRaiseAnim = ObjectAnimator.ofFloat(TextView_menu_fragment_title, "translationY", -distance)

        val subtitleRaiseAnim = ObjectAnimator.ofFloat(TextView_menu_fragment_subtitle, "translationY", -distance)

        animSet.playTogether(portraitRaiseAnim, titleRaiseAnim, subtitleRaiseAnim)
        animSet.duration = 300

        animSet.start()


    }

    private fun fadeOutHeader(duration: Long) {
        val portraitFadeAnim = ObjectAnimator.ofFloat(CirclePortraitView_menu_fragment, "alpha", 0f)
        val titleFadeAnim = ObjectAnimator.ofFloat(TextView_menu_fragment_title, "alpha", 0f)
        val subtitleFadeAnim = ObjectAnimator.ofFloat(TextView_menu_fragment_subtitle, "alpha", 0f)

        val animatorSet = AnimatorSet()
        animatorSet.playTogether(
            portraitFadeAnim,
            titleFadeAnim,
            subtitleFadeAnim
        )

        animatorSet.duration = duration


        animatorSet.start()
    }

    public fun fadeHeaderIn() {
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
            subtitleFadeAnim
        )

        animatorSet.duration = 400
        animatorSet.start()
    }


    override fun onMenuChangeFadeOutFinished() {
        mCallback.onMenuChangeFadeOutFinished()

    }


    override fun onMenuItemSelected(title: String) {
        if (isNewFragmentSelected(title)) {
            dropMenu()
            fadeOutHeader(100)
            mCallback.onNewFragmentMenuItemSelected(title)
        } else {
            mCallback.onNonNewFragmentMenuItemSelected(title)
        }
    }

    private fun isNewFragmentSelected(title: String): Boolean {
        return title == MenuFragment.SKILLS_STRING ||
                title == MenuFragment.ABOUT_ME_STRING ||
                title == MenuFragment.LINKEDIN_STRING ||
                title == MenuFragment.EXPERIENCE_STRING
    }


    data class MenuItem(val title: String, val imageRes: Int, val color: Int)


}