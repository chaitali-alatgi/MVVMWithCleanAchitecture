package com.example.mvvmwithhcleanarchitecture.domain.usecase

import com.example.mvvmwithhcleanarchitecture.domain.model.Restaurant
import com.example.mvvmwithhcleanarchitecture.domain.repository.RestaurantRepository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GetRestaurantListUseCase @Inject constructor(private val restaurantRepository: RestaurantRepository) {
    sealed class Result {
        object loading: Result()
        data class Success(val restaurants: List<Restaurant>): Result()
        data class Failure(val throwable: Throwable): Result()
    }

    fun execute(lat: Double, lng: Double, offset: Int, limit: Int): Observable<Result> {
        return restaurantRepository.getRestaurantList(lat, lng, offset, limit)
            .toObservable()
            .map { Result.Success(it) as Result }
            .onErrorReturn { Result.Failure(it) }
    }
}