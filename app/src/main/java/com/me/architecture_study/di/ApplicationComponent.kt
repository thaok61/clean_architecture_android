package com.me.architecture_study.di

import android.content.Context
import com.me.architecture_study.ArchitectureApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ViewModelBuilder::class,
        ApplicationModule::class,
        AndroidSupportInjectionModule::class,
        ActivityModuleBuilder::class,
        MainActivityModule::class]
)
interface ApplicationComponent : AndroidInjector<ArchitectureApplication> {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ApplicationComponent
    }
}