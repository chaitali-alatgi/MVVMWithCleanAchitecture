package com.example.mvvmwithhcleanarchitecture

import android.app.Application
import com.example.mvvmwithhcleanarchitecture.app.di.application.ApplicationComponent
import com.example.mvvmwithhcleanarchitecture.app.di.application.ApplicationModule
import com.example.mvvmwithhcleanarchitecture.app.di.application.DaggerApplicationComponent
import com.example.mvvmwithhcleanarchitecture.app.di.screen.ScreenModule

class BaseApplication: Application() {

    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        inject()
    }

    private fun inject() {
        component = DaggerApplicationComponent.builder().applicationModule(ApplicationModule(this)).build()
        component.inject(this)
    }
}