package com.example.myapplication.activities

import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.fragments.MenuFragment
import com.example.myapplication.util.OttoBusClasses
import com.squareup.otto.Subscribe
import kotlinx.android.synthetic.main.activity_main.*
import com.google.android.material.appbar.AppBarLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import com.example.myapplication.fragments.AboutMeFragment
import com.example.myapplication.fragments.ExperienceFragment
import com.example.myapplication.util.FragmentUtil
import com.example.myapplication.views.ToolBarCustomView


class MainActivity : BaseActivity() {


    private var mToolbar: ToolBarCustomView? = null
    private var mPendingFragmentTitle = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(Activity_toolbar)
        mToolbar = ToolBarCustomView(this)

        supportActionBar?.customView = mToolbar
        supportActionBar?.setDisplayShowCustomEnabled(true)




        initFragment()
        Activity_collapse_bar.title = " "


        val params = Activity_AppBarLayout.layoutParams as CoordinatorLayout.LayoutParams
        val behavior = params.behavior as AppBarLayout.Behavior?
        behavior!!.setDragCallback(object : AppBarLayout.Behavior.DragCallback() {
            override fun canDrag(appBarLayout: AppBarLayout): Boolean {
                return false
            }
        })

    }

    private fun initFragment() {
        val sfm = supportFragmentManager.beginTransaction()
        sfm.add(MainActivity_Fragment_Container.id, MenuFragment.create())
        sfm.commit()
    }

    private fun showToolbar(show: Boolean){
        supportActionBar?.setHomeButtonEnabled(show)
        supportActionBar?.setDisplayHomeAsUpEnabled(show)
    }

    private fun handleFragmentSelected(title: String){
        var fragment: Fragment = Fragment()
        when (title){
            MenuFragment.ABOUT_ME_STRING -> fragment = AboutMeFragment()
            MenuFragment.EXPERIENCE_STRING -> fragment = ExperienceFragment()
        }

        FragmentUtil.changeFragment(supportFragmentManager, fragment)

    }


    @Subscribe
    public fun onInfoFragmentGoBackEvent(e: OttoBusClasses.InfoFragmentGoBackEvent) {
        mToolbar?.fadeOutTitle()
    }

    @Subscribe
    public fun onMenuFragmentItemSelectedEvent(e: OttoBusClasses.MenuFragmentItemSelectedEvent) {
        Activity_AppBarLayout.setExpanded(false, true)
        mToolbar?.setTitle(e.title)
        mPendingFragmentTitle = e.title
    }

    @Subscribe
    public fun onMenuFragmentReturnToMenuEvent(e: OttoBusClasses.MenuFragmentReturnToMenuEvent) {
        showToolbar(false)
        Activity_AppBarLayout.setExpanded(true, true)
        mToolbar?.fadeOutTitle()
    }

    @Subscribe
    public fun onMenuFragmentExitAnimationFinishedEvent(e: OttoBusClasses.MenuFragmentExitAnimationFinishedEvent) {
        showToolbar(true)
        handleFragmentSelected(mPendingFragmentTitle)
        mToolbar?.fadeInTitle()
    }

    @Subscribe
    public fun onReturnToMenuEvent(e: OttoBusClasses.ReturnToMenuEvent) {
        mPendingFragmentTitle = ""
    }


}
