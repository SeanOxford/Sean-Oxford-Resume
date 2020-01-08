package com.example.myapplication

import android.app.Application
import android.content.res.Configuration
import com.example.myapplication.dagger.DaggerMyComponent
import com.example.myapplication.dagger.MyComponent
import com.example.myapplication.dagger.MyModule

class MyApp : Application() {

    lateinit var myComponent: MyComponent


    override fun onCreate() {
        super.onCreate()
        myComponent = initDagger(this)

    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

    }

    override fun onLowMemory() {
        super.onLowMemory()

    }


    private fun initDagger(app: MyApp): MyComponent =
        DaggerMyComponent.builder().myModule((MyModule(app)))
            .build()


}