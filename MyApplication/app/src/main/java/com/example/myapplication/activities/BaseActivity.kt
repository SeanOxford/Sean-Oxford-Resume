package com.example.myapplication.activities

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.MyApp
import com.squareup.otto.Bus
import javax.inject.Inject

public abstract class BaseActivity : AppCompatActivity() {


    @Inject
    public lateinit var mBus: Bus

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as MyApp).myComponent.inject(this)

    }

    override fun onStart() {
        super.onStart()
        mBus.register(this)

    }

    override fun onStop() {
        super.onStop()
        mBus.unregister(this)
    }


}