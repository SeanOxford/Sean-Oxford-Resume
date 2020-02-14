package com.oxford.resume.dagger

import com.oxford.resume.activities.BaseActivity
import com.oxford.resume.activities.handlers.BackPressHandler
import com.oxford.resume.fragments.BaseFragment
import com.oxford.resume.views.MenuFragmentGridRecyclerView
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