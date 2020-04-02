package com.example.mvvmwithhcleanarchitecture.data

import com.example.mvvmwithhcleanarchitecture.data.response.RestaurantResponse
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class RestaurantApi @Inject constructor(private val restaurantNetworkEndpoint: RestaurantNetworkEndpoint) {
    fun getRestaurantList(lat: Double, lng: Double, offset: Int, limit: Int): Single<List<RestaurantResponse>> {
        return restaurantNetworkEndpoint.getRestaurantList(lat, lng, offset, limit)
    }

    fun getRestaurant(id: Int) : Single<RestaurantResponse> {
        return restaurantNetworkEndpoint.getRestaurant(id)
    }
}