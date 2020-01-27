package com.seanoxford.resume

import android.app.Application
import android.content.res.Configuration
import com.seanoxford.resume.dagger.DaggerMyComponent
import com.seanoxford.resume.dagger.MyComponent
import com.seanoxford.resume.dagger.MyModule

class ResumeApp : Application() {

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


    private fun initDagger(app: ResumeApp): MyComponent =
        DaggerMyComponent.builder().myModule((MyModule(app)))
            .build()


}