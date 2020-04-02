package com.example.mvvmwithhcleanarchitecture.app.di.application

import com.example.mvvmwithhcleanarchitecture.data.RestaurantApi
import com.example.mvvmwithhcleanarchitecture.data.mapper.RestaurantMapper
import com.example.mvvmwithhcleanarchitecture.data.repository.RestaurantRepositoryImpl
import com.example.mvvmwithhcleanarchitecture.domain.repository.RestaurantRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun providesRestaurantRepository(restaurantApi: RestaurantApi, restaurantMapper: RestaurantMapper): RestaurantRepository {
        return RestaurantRepositoryImpl(restaurantApi, restaurantMapper)
    }
}