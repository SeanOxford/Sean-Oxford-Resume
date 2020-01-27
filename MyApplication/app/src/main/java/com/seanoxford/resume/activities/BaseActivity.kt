package com.seanoxford.resume.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.seanoxford.resume.ResumeApp
import com.seanoxford.resume.activities.handlers.BackPressHandler
import com.squareup.otto.Bus
import javax.inject.Inject

public abstract class BaseActivity : AppCompatActivity() {


    @Inject
    protected lateinit var mBus: Bus

    private var mBackPressHandler: BackPressHandler? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as ResumeApp).myComponent.inject(this)
        mBackPressHandler = BackPressHandler(application)

    }

    override fun onStart() {
        super.onStart()
        mBus.register(this)
        mBackPressHandler?.registerBus()

    }

    override fun onStop() {
        super.onStop()
        mBus.unregister(this)
        mBackPressHandler?.unregisterBus()
    }


    override fun onBackPressed() {
        if (mBackPressHandler!!.isCustomBehavior()) {
            mBackPressHandler?.executeCustomBehavior()
        } else {
            super.onBackPressed()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> mBackPressHandler?.executeCustomBehavior()
        }
        if(!mBackPressHandler!!.isCustomBehavior()){
            onBackPressed()
        }

        return true
    }


}