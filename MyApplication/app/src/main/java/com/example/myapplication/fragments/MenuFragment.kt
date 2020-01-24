package com.example.myapplication.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.util.OttoBusClasses
import com.example.myapplication.views.fragmentViews.BaseFragmentView
import com.example.myapplication.views.fragmentViews.MenuFragmentView

class MenuFragment : BaseFragment(), MenuFragmentView.MenuFragmentViewCallbacks {


    override fun getMainView(): BaseFragmentView {
        return MenuFragmentView(context, createMainMenu(), this)
    }

    private var mView: MenuFragmentView? = null

    companion object {
        const val ABOUT_ME_STRING = "About Me"
        const val EXPERIENCE_STRING = "Experience"
        const val LINKEDIN_STRING = "LinkedIn"
        const val SKILLS_STRING = "Skills"
        const val CONTACT_ME_STRING = "Contact Me"
        const val EXERCISES_STRING = "Exercises"


        const val FIBONACCI_STRING = "Fibonacci"
        const val ARRAYS_STRING = "Arrays"
        const val LINKED_LIST_STRING = "Linked List"
        const val BINARY_TREE_STRING = "Binary Tree"
        const val STRING_STRING = "Strings"
        const val BACK_TO_MENU_STRING = "Back To Menu"

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
            mView?.fragmentAnimateHeaderIn()
        }

        return mView
    }

    override fun onStart() {
        super.onStart()
        mBus.post(OttoBusClasses.MenuFragmentReturnToMenuEvent())
    }


    private fun createMainMenu(): ArrayList<MenuFragmentView.MenuItem> {
        val menuItemList = ArrayList<MenuFragmentView.MenuItem>()
        val color = resources.getColor(R.color.main_menu_tint, null)


        menuItemList.add(MenuFragmentView.MenuItem(ABOUT_ME_STRING, R.drawable.menu_icon_about_me, color))
        menuItemList.add(MenuFragmentView.MenuItem(EXPERIENCE_STRING, R.drawable.menu_icon_experience, color))
        menuItemList.add(MenuFragmentView.MenuItem(LINKEDIN_STRING, R.drawable.menu_icon_linkedin, color))
        menuItemList.add(MenuFragmentView.MenuItem(SKILLS_STRING, R.drawable.menu_icon_skills, color))
        menuItemList.add(MenuFragmentView.MenuItem(CONTACT_ME_STRING, R.drawable.menu_icon_mail, color))
        menuItemList.add(MenuFragmentView.MenuItem(EXERCISES_STRING, R.drawable.menu_icon_exercises, color))

        return menuItemList
    }

    private fun createExerciseMenu(): ArrayList<MenuFragmentView.MenuItem> {
        val menuItemList = ArrayList<MenuFragmentView.MenuItem>()
        val color = resources.getColor(R.color.exercise_menu_tint, null)

        menuItemList.add(MenuFragmentView.MenuItem(FIBONACCI_STRING, R.drawable.menu_icon_ex_fib, color))
        menuItemList.add(MenuFragmentView.MenuItem(ARRAYS_STRING, R.drawable.menu_icon_ex_arrays, color))
        menuItemList.add(MenuFragmentView.MenuItem(LINKED_LIST_STRING, R.drawable.menu_icon_ex_linked_list, color))
        menuItemList.add(MenuFragmentView.MenuItem(BINARY_TREE_STRING, R.drawable.menu_icon_ex_binary, color))
        menuItemList.add(MenuFragmentView.MenuItem(STRING_STRING, R.drawable.menu_icon_ex_string, color))
        menuItemList.add(MenuFragmentView.MenuItem(BACK_TO_MENU_STRING, R.drawable.menu_icon_ex_back, color))

        return menuItemList
    }

    override fun onMenuChangeFadeOutFinished() {
        mView?.setNewData(createExerciseMenu())

    }

    private fun switchToExerciseMenu() {
        mView?.fadeOutMenuButtons()
//        mView?.setNewData(createExerciseMenu())
    }


    override fun onNewFragmentMenuItemSelected(title: String) {
        mBus.post(OttoBusClasses.MenuFragmentGoToNewFragmentEvent(title))
    }

    override fun onNonNewFragmentMenuItemSelected(title: String) {
        when (title) {
            EXERCISES_STRING -> switchToExerciseMenu()
        }

        mBus.post(OttoBusClasses.MenuFragmentNonFragmentMenuItemSelectedEvent(title))
    }

    override fun onLeaveAnimFinished() {
        mBus.post(OttoBusClasses.MenuFragmentExitAnimationFinishedEvent())
    }


}