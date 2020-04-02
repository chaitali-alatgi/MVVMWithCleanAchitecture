package com.example.mvvmwithhcleanarchitecture.app.di.application

import com.example.mvvmwithhcleanarchitecture.BaseApplication
import com.example.mvvmwithhcleanarchitecture.app.di.screen.ScreenComponent
import com.example.mvvmwithhcleanarchitecture.app.di.screen.ScreenModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, RepositoryModule::class, EndpointModule::class])
interface ApplicationComponent {

    fun inject(activity: BaseApplication)

    fun plus(screenModule: ScreenModule): ScreenComponent
}