package com.example.myapplication.dagger

import com.example.myapplication.activities.BaseActivity
import com.example.myapplication.fragments.BaseFragment
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [MyModule::class])
interface MyComponent {
    fun inject(baseActivity: BaseActivity)
    fun inject(baseFragment: BaseFragment)


}