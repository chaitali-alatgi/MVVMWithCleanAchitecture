package com.example.mvvmwithhcleanarchitecture.domain.repository

import com.example.mvvmwithhcleanarchitecture.domain.model.Restaurant
import io.reactivex.rxjava3.core.Single

interface RestaurantRepository {

    fun getRestaurantList(lat: Double, lng: Double, offset: Int, limit: Int): Single<List<Restaurant>>

    fun getRestaurant(id: Int): Single<Restaurant>

}