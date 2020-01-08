package com.example.myapplication.dagger

import android.app.Application
import android.content.Context
import com.squareup.otto.Bus
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class MyModule(private val app: Application) {

    @Provides
    @Singleton
    fun provideContext(): Context = app

//    @Provides
//    @Singleton
//    fun provideOttoBus(): mBus {
//        val bus = mBus()
//        return bus
//    }


    @Provides
    @Singleton
    fun provideBus(): Bus = Bus()

}