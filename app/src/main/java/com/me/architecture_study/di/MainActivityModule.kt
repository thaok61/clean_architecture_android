package com.me.architecture_study.di

import androidx.lifecycle.ViewModel
import com.me.architecture_study.view.MainActivity
import com.me.architecture_study.view.UsersFragment
import com.me.architecture_study.viewmodel.UserViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class MainActivityModule {
    @ContributesAndroidInjector
    abstract fun inject(): UsersFragment

    @ContributesAndroidInjector
    abstract fun injectMain(): MainActivity

    @Binds
    @IntoMap
    @ViewModelKey(UserViewModel::class)
    abstract fun bindViewModel(vm: UserViewModel): ViewModel
}