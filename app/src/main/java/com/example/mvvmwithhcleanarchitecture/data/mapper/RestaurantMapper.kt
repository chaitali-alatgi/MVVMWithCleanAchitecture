package com.example.mvvmwithhcleanarchitecture.data.mapper

import com.example.mvvmwithhcleanarchitecture.data.response.RestaurantResponse
import com.example.mvvmwithhcleanarchitecture.domain.model.Restaurant
import javax.inject.Inject

class RestaurantMapper @Inject constructor() {
    fun map(responseList: List<RestaurantResponse>): List<Restaurant> {
        return responseList.map { (map(it)) }
    }

    fun map(response: RestaurantResponse): Restaurant {
        return Restaurant(
            id= response.id,
            name = response.name,
            description = response.description,
            coverImgUrl = response.coverImgUrl,
            status = response.status,
            deliveryFee = response.deliveryFee
        )
    }
}