package com.example.mvvmwithhcleanarchitecture.app.di.application

import com.example.mvvmwithhcleanarchitecture.data.RestaurantNetworkEndpoint
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class EndpointModule {

    @Provides
    @Singleton
    fun providesRestaurantEndpoint(retrofit: Retrofit): RestaurantNetworkEndpoint {
        return retrofit.create(RestaurantNetworkEndpoint::class.java)
    }
}