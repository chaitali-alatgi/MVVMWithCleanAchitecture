package com.example.mvvmwithhcleanarchitecture.data

import com.example.mvvmwithhcleanarchitecture.data.response.RestaurantResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RestaurantNetworkEndpoint {
    @GET("restaurant/")
    fun getRestaurantList(@Query("lat") lat: Double, @Query("lng") lng: Double,
                          @Query("offset") offset: Int, @Query("limit") limit: Int): Single<List<RestaurantResponse>>

    @GET("restaurant/{id}")
    fun getRestaurant(@Path("id") id: Int): Single<RestaurantResponse>
}