package com.example.myapplication.activities

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.fragments.MenuFragment
import com.example.myapplication.util.OttoBusClasses
import com.squareup.otto.Subscribe
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(Activity_toolbar)

        initFragment()
    }

    private fun initFragment() {
        val sfm = supportFragmentManager.beginTransaction()
        sfm.add(MainActivity_Fragment_Container.id, MenuFragment.create())
        sfm.commit()


    }


    override fun onResume() {
        super.onResume()

        Activity_AppBarLayout.setExpanded(true)
        Activity_AppBarLayout.expandToThird()


    }



    @Subscribe
    public fun onButtonClickEvent(e: OttoBusClasses.ButtonEvent){
        Log.d("nnn", String.format("RECIEVE"))


    }


}
