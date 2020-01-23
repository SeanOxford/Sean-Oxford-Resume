package com.example.myapplication.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.WindowManager
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.fragments.*
import com.example.myapplication.util.AppUtil
import com.example.myapplication.util.FragmentUtil
import com.example.myapplication.util.OttoBusClasses
import com.example.myapplication.views.ToolBarCustomView
import com.example.myapplication.views.fragmentViews.ExperienceFragmentView
import com.google.android.material.appbar.AppBarLayout
import com.squareup.otto.Subscribe
import kotlinx.android.synthetic.main.activity_main.*


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

        ViewCompat.setNestedScrollingEnabled(ScrollView_main_activity, false)


        val params = Activity_AppBarLayout.layoutParams as CoordinatorLayout.LayoutParams
        val behavior = params.behavior as AppBarLayout.Behavior?
        behavior!!.setDragCallback(object : AppBarLayout.Behavior.DragCallback() {
            override fun canDrag(appBarLayout: AppBarLayout): Boolean {
                return false
            }
        })

    }

    override fun onStart() {
        super.onStart()
        Handler().postDelayed({
            MainActivity_Fragment_Container.requestFocus()
        }, 1000)



    }


    private fun initFragment() {
        val sfm = supportFragmentManager.beginTransaction()
        sfm.add(MainActivity_Fragment_Container.id, MenuFragment.create())
        sfm.commit()
    }

    private fun showToolbar(show: Boolean) {
        supportActionBar?.setHomeButtonEnabled(show)
        supportActionBar?.setDisplayHomeAsUpEnabled(show)
    }


    private fun goToLinkedIn() {
        val uriUrl = Uri.parse(resources.getString(R.string.linked_in))
        val launchBrowser = Intent(Intent.ACTION_VIEW, uriUrl)
        startActivity(launchBrowser)
    }


    private fun handleNewFragmentMenuItemSelected(title: String) {
        Log.d("nnn", String.format("handle select"))
        var fragment: Fragment = Fragment()
        when (title) {
            MenuFragment.ABOUT_ME_STRING -> fragment = AboutMeFragment()
            MenuFragment.EXPERIENCE_STRING -> fragment = ExperienceFragment()
            MenuFragment.SKILLS_STRING -> fragment = SkillsFragment()
            MenuFragment.LINKEDIN_STRING -> fragment = LinkedInFragment()
        }

        FragmentUtil.changeFragment(supportFragmentManager, fragment)
    }

    private fun handleNonNewFragmentMenuItemSelected(title: String){
        when(title){
            MenuFragment.CONTACT_ME_STRING -> {
                Log.d("nnn", String.format("dafuq"))
                AppUtil.sendEmail(this)
            }
            MenuFragment.EXERCISES_STRING -> switchToExercisesMenu()
        }
    }


    private fun switchToExercisesMenu(){
        ScreenFractionImageView.fadeToGreen()
    }

    @Subscribe
    public fun onInfoFragmentGoBackEvent(e: OttoBusClasses.InfoFragmentGoBackEvent) {
        mToolbar?.fadeOutTitle()
    }

    @Subscribe
    public fun onMenuFragmentNonFragmentMenuItemSelectedEvent(e: OttoBusClasses.MenuFragmentNonFragmentMenuItemSelectedEvent) {
        Log.d("nnn", String.format("clicked: %s", e.title))
        handleNonNewFragmentMenuItemSelected(e.title)
        mToolbar?.setTitle(e.title)
        mPendingFragmentTitle = e.title
    }

    @Subscribe
    public fun onMenuFragmentGoToNewFragmentEvent(e: OttoBusClasses.MenuFragmentGoToNewFragmentEvent) {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        )
        mToolbar?.setTitle(e.title)
        mPendingFragmentTitle = e.title
        Activity_AppBarLayout.setExpanded(false, true)
    }

    @Subscribe
    public fun onMenuFragmentExitAnimationFinishedEvent(e: OttoBusClasses.MenuFragmentExitAnimationFinishedEvent) {
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        showToolbar(true)
        handleNewFragmentMenuItemSelected(mPendingFragmentTitle)
        mToolbar?.fadeInTitle()
    }


    @Subscribe
    public fun onMenuFragmentReturnToMenuEvent(e: OttoBusClasses.MenuFragmentReturnToMenuEvent) {
        showToolbar(false)
        Activity_AppBarLayout.setExpanded(true, true)
        mToolbar?.fadeOutTitle()
    }


    @Subscribe
    public fun onReturnToMenuEvent(e: OttoBusClasses.ReturnToMenuEvent) {
        mPendingFragmentTitle = ""
    }

    @Subscribe
    public fun onGoToAppStoreEvent(e: OttoBusClasses.GoToAppStoreEvent) {
        var url = ""
        when (e.appId) {
            ExperienceFragmentView.INK_CLICK_ID -> url =
                resources.getString(R.string.ink_url)
            ExperienceFragmentView.POSTAGRAM_CLICK_ID -> url =
                resources.getString(R.string.postagram_url)
        }

        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(browserIntent)
    }

    @Subscribe
    public fun onGoToLinkedInEvent(e: OttoBusClasses.GoToLinkedInEvent) {
        goToLinkedIn()
    }

    @Subscribe
    public fun onEmailClickedEvent(e: OttoBusClasses.EmailClickedEvent) {
        Log.d("nnn", String.format("emailGuy"))
        AppUtil.sendEmail(this)
    }

    @Subscribe
    public fun onSendTextMsgEvent(e: OttoBusClasses.SendTextMsgEvent) {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.fromParts("sms", resources.getString(R.string.phone_number), null)
            )
        )
    }


}
