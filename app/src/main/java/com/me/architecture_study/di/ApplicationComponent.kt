package com.me.architecture_study.di

import android.content.Context
import com.me.architecture_study.ArchitectureApplication
import com.me.architecture_study.MainActivity
import com.me.architecture_study.viewmodel.UserViewModel
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, AndroidSupportInjectionModule::class, ActivityModuleBuilder::class])
interface ApplicationComponent : AndroidInjector<ArchitectureApplication> {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ApplicationComponent
    }
}