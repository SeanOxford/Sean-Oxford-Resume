package com.example.myapplication.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.util.OttoBusClasses
import com.example.myapplication.views.MenuFragmentView

class MenuFragment : BaseFragment(), MenuFragmentView.MenuFragmentViewCallbacks {



    val PERSONAL_INFO_STRING = "Personal Info"
    val EXPERIENCE_STRING = "Experience"
    val LINKEDIN_STRING = "LinkedIn"
    val SKILLS_STRING = "Skills"
    val SCHEDULE_INTERVIEW_STRING = "Schedule an Interview"
    val EXERCISES_STRING = "Exercises"

    private var mView: MenuFragmentView? = null

    companion object {
        fun create(): MenuFragment = MenuFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if(mView == null){
            mView = MenuFragmentView(context, createMenuInfo(), this)
        } else {
            mView?.animateIn()
        }

        return  mView
    }

    override fun onStart() {
        super.onStart()
        mBus.post(OttoBusClasses.MenuFragmentReturnToFragmentEvent())
    }



    private fun createMenuInfo() :  ArrayList<MenuFragmentView.MenuItem>{
        val menuItemList = ArrayList<MenuFragmentView.MenuItem>()
        menuItemList.add(MenuFragmentView.MenuItem(PERSONAL_INFO_STRING, R.drawable.menu_icon_about_me))
        menuItemList.add(MenuFragmentView.MenuItem(EXPERIENCE_STRING, R.drawable.menu_icon_experience))
        menuItemList.add(MenuFragmentView.MenuItem(LINKEDIN_STRING, R.drawable.menu_icon_linkedin))
        menuItemList.add(MenuFragmentView.MenuItem(SKILLS_STRING, R.drawable.menu_icon_skills))
        menuItemList.add(MenuFragmentView.MenuItem(SCHEDULE_INTERVIEW_STRING, R.drawable.menu_icon_interview))
        menuItemList.add(MenuFragmentView.MenuItem(EXERCISES_STRING, R.drawable.menu_icon_exercises))

        return menuItemList
    }




    override fun onMenuItemSelected(title: String) {
        mBus.post(OttoBusClasses.MenuFragmentItemSelectedEvent(title))
    }

    override fun onLeaveAnimFinished() {
        mBus.post(OttoBusClasses.MenuFragmentExitAnimationFinishedEvent())
    }




}