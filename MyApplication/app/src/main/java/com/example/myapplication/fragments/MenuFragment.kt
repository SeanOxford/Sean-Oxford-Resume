package com.example.myapplication.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.util.OttoBusClasses
import com.example.myapplication.views.fragmentViews.BaseFragmentView
import com.example.myapplication.views.fragmentViews.MenuFragmentView

class MenuFragment : BaseFragment(), MenuFragmentView.MenuFragmentViewCallbacks {


    override fun getMainView(): BaseFragmentView {
        return MenuFragmentView(context, createMenuInfo(), this)
    }

    private var mView: MenuFragmentView? = null

    companion object {
        const val ABOUT_ME_STRING = "About Me"
        const val EXPERIENCE_STRING = "Experience"
        const val LINKEDIN_STRING = "LinkedIn"
        const val SKILLS_STRING = "Skills"
        const val CONTACT_ME_STRING = "Contact Me"
        const val EXERCISES_STRING = "Exercises"

        fun create(): MenuFragment = MenuFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (mView == null) {
            mView = getMainView() as MenuFragmentView
        } else {
            mView?.animateIn()
        }

        return mView
    }

    override fun onStart() {
        super.onStart()
        mBus.post(OttoBusClasses.MenuFragmentReturnToMenuEvent())
    }


    private fun createMenuInfo(): ArrayList<MenuFragmentView.MenuItem> {
        val menuItemList = ArrayList<MenuFragmentView.MenuItem>()
        menuItemList.add(MenuFragmentView.MenuItem(ABOUT_ME_STRING, R.drawable.menu_icon_about_me))
        menuItemList.add(MenuFragmentView.MenuItem(EXPERIENCE_STRING, R.drawable.menu_icon_experience))
        menuItemList.add(MenuFragmentView.MenuItem(LINKEDIN_STRING, R.drawable.menu_icon_linkedin))
        menuItemList.add(MenuFragmentView.MenuItem(SKILLS_STRING, R.drawable.menu_icon_skills))
        menuItemList.add(MenuFragmentView.MenuItem(CONTACT_ME_STRING, R.drawable.menu_icon_mail))
        menuItemList.add(MenuFragmentView.MenuItem(EXERCISES_STRING, R.drawable.menu_icon_exercises))

        return menuItemList
    }


    override fun onNewFragmentMenuItemSelected(title: String) {
        Log.d("nnn", String.format("333"))

        mBus.post(OttoBusClasses.MenuFragmentGoToNewFragmentEvent(title))
    }

    override fun onNonNewFragmentMenuItemSelected(title: String) {
        mBus.post(OttoBusClasses.MenuFragmentNonFragmentMenuItemSelectedEvent(title))
    }

    override fun onLeaveAnimFinished() {
        mBus.post(OttoBusClasses.MenuFragmentExitAnimationFinishedEvent())
    }


}