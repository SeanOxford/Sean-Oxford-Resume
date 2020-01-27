package com.seanoxford.resume.dagger

import com.seanoxford.resume.activities.BaseActivity
import com.seanoxford.resume.activities.handlers.BackPressHandler
import com.seanoxford.resume.fragments.BaseFragment
import com.seanoxford.resume.views.MenuFragmentGridRecyclerView
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [MyModule::class])
interface MyComponent {
    fun inject(baseActivity: BaseActivity)
    fun inject(baseFragment: BaseFragment)
    fun inject(backPressHandler: BackPressHandler)
    fun inject(menuFragmentGridRecyclerView: MenuFragmentGridRecyclerView)


}