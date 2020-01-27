package com.seanoxford.resume.views.fragmentViews

import android.content.Context
import com.seanoxford.resume.R
import com.seanoxford.resume.activities.anim.*
import com.seanoxford.resume.fragments.MenuFragment
import com.seanoxford.resume.views.MenuFragmentGridRecyclerView
import kotlinx.android.synthetic.main.layout_menu_fragment.view.*

class MenuFragmentView(context: Context?, menuItemList: ArrayList<MenuItem>, callback: MenuFragmentViewCallbacks) :
    BaseFragmentView(context), MenuFragmentGridRecyclerView.GridViewCallback {

    interface MenuFragmentViewCallbacks {
        fun onNonNewFragmentMenuItemSelected(title: String)
        fun onNewFragmentMenuItemSelected(title: String)
        fun onLeaveAnimFinished()
    }

    private val mMenuItemList = menuItemList
    private val mCallback = callback
    private lateinit var mRecyclerView: MenuFragmentGridRecyclerView


    init {
        initGridView()
    }

    override fun getLayout(): Int {
        return R.layout.layout_menu_fragment
    }


    public fun setNewData(data: List<MenuItem>, menuType: Int) {
        mRecyclerView.switchMenu(data)
        when (menuType) {
            MenuFragment.MENU_TYPE_EXERCISE -> animateHeaderToExercise()
            MenuFragment.MENU_TYPE_MAIN -> animateHeaderToMain()
        }
    }


    private fun animateHeaderToExercise() {
        HeaderToExerciseAnimation(
            CirclePortraitView_menu_fragment,
            TextView_menu_fragment_title,
            TextView_menu_fragment_subtitle,
            TextView_menu_fragment_header_exercises
        ).startAnimation()
    }

    private fun animateHeaderToMain() {
        ExerciseToHeaderAnimation(
            CirclePortraitView_menu_fragment,
            TextView_menu_fragment_title,
            TextView_menu_fragment_subtitle,
            TextView_menu_fragment_header_exercises
        ).startAnimation()
    }

    private fun animateOutToNewFragment() {
        GoToFragmentAnimation(
            mRecyclerView,
            CirclePortraitView_menu_fragment,
            TextView_menu_fragment_title,
            TextView_menu_fragment_subtitle,
            TextView_menu_fragment_header_exercises,
            object : GoToFragmentAnimation.Callback {
                override fun onDropMenuAnimFinish() {
                    mCallback.onLeaveAnimFinished()
                }
            }).startAnimation()

    }

    public fun animateInFromFragment(menuType: Int) {
        if (menuType == MenuFragment.MENU_TYPE_MAIN) {
            ReturnFromFragmentAnimation(
                mRecyclerView,
                CirclePortraitView_menu_fragment,
                TextView_menu_fragment_title,
                TextView_menu_fragment_subtitle
            ).startAnimation()
        } else if (menuType == MenuFragment.MENU_TYPE_EXERCISE) {
            ReturnFromExerciseFragmentAnimation(
                mRecyclerView,
                TextView_menu_fragment_header_exercises
            ).startAnimation()
        }
    }


    private fun initGridView() {
        mRecyclerView = MenuFragmentGridRecyclerView(
            context,
            mMenuItemList,
            this
        )

        FrameLayout_menu_fragment_grid_container.addView(mRecyclerView)
    }


    override fun onMenuItemSelected(title: String) {
        if (isNewFragmentSelected(title)) {
            animateOutToNewFragment()
            mCallback.onNewFragmentMenuItemSelected(title)
        } else {
            mCallback.onNonNewFragmentMenuItemSelected(title)
        }
    }

    private fun isNewFragmentSelected(title: String): Boolean {
        return title == MenuFragment.SKILLS_STRING ||
                title == MenuFragment.ABOUT_ME_STRING ||
                title == MenuFragment.LINKEDIN_STRING ||
                title == MenuFragment.EXPERIENCE_STRING ||
                title == MenuFragment.FIBONACCI_STRING
    }


    data class MenuItem(val title: String, val imageRes: Int, val color: Int)


}