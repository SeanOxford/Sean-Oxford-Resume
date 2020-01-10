package com.example.myapplication.activities

import android.os.Bundle
import android.util.Log
import com.example.myapplication.R
import com.example.myapplication.fragments.MenuFragment
import com.example.myapplication.util.OttoBusClasses
import com.squareup.otto.Subscribe
import kotlinx.android.synthetic.main.activity_main.*
import com.google.android.material.appbar.AppBarLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.example.myapplication.fragments.AboutMeFragment
import com.example.myapplication.util.FragmentUtil


class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(Activity_toolbar)

        initFragment()
        Activity_toolbar.title = "dicks"
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


    @Subscribe
    public fun onMenuFragmentItemSelectedEvent(e: OttoBusClasses.MenuFragmentItemSelectedEvent) {
        Activity_AppBarLayout.setExpanded(false, true)
    }

    @Subscribe
    public fun onMenuFragmentReturnToFragmentEvent(e: OttoBusClasses.MenuFragmentReturnToFragmentEvent) {
        Activity_AppBarLayout.setExpanded(true, true)
    }

    @Subscribe
    public fun onMenuFragmentExitAnimationFinishedEvent(e: OttoBusClasses.MenuFragmentExitAnimationFinishedEvent) {
        FragmentUtil.changeFragment(supportFragmentManager, AboutMeFragment())
    }

    @Subscribe
    public fun onReturnToMenuEvent(e: OttoBusClasses.ReturnToMenuEvent) {

    }


}
