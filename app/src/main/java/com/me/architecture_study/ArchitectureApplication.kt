package com.me.architecture_study

import com.me.architecture_study.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

open class ArchitectureApplication : DaggerApplication(){
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return  DaggerApplicationComponent.factory().create(applicationContext)
    }
}