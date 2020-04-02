package com.example.mvvmwithhcleanarchitecture.data.repository

import com.example.mvvmwithhcleanarchitecture.data.RestaurantApi
import com.example.mvvmwithhcleanarchitecture.data.mapper.RestaurantMapper
import com.example.mvvmwithhcleanarchitecture.domain.model.Restaurant
import com.example.mvvmwithhcleanarchitecture.domain.repository.RestaurantRepository
import io.reactivex.rxjava3.core.Single

class RestaurantRepositoryImpl (
    private val restaurantApi: RestaurantApi,
    private val restaurantMapper: RestaurantMapper
): RestaurantRepository {
    override fun getRestaurantList(lat: Double, lng: Double, offset: Int, limit: Int): Single<List<Restaurant>> {
        return restaurantApi.getRestaurantList(lat, lng, offset, limit)
            .map { restaurantMapper.map(it) }
    }

    override fun getRestaurant(id: Int) : Single<Restaurant> {
        return restaurantApi.getRestaurant(id)
            .map { restaurantMapper.map(it) }
    }
}