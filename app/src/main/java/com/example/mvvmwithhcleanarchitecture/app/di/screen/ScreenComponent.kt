package com.example.mvvmwithhcleanarchitecture.app.di.screen

import com.example.mvvmwithhcleanarchitecture.app.di.scope.PerScreen
import com.example.mvvmwithhcleanarchitecture.app.presentation.imagedetail.ImageDetailActivity
import com.example.mvvmwithhcleanarchitecture.app.presentation.main.MainActivity
import com.example.mvvmwithhcleanarchitecture.app.presentation.restaurantdetail.RestaurantDetailActivity
import dagger.Subcomponent

@PerScreen
@Subcomponent(modules = [ScreenModule::class])
interface ScreenComponent {
    fun inject(mainActivity: MainActivity)

    fun inject(restaurantDetailActivity: RestaurantDetailActivity)

    fun inject(imageDetailActivity: ImageDetailActivity)
}