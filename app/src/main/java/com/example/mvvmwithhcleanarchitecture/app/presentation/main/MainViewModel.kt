package com.example.mvvmwithhcleanarchitecture.app.presentation.main

import android.os.Bundle
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import com.example.mvvmwithhcleanarchitecture.app.ext.addTo
import com.example.mvvmwithhcleanarchitecture.app.presentation.imagedetail.ImageDetailActivity
import com.example.mvvmwithhcleanarchitecture.app.presentation.restaurantdetail.RestaurantDetailActivity
import com.example.mvvmwithhcleanarchitecture.domain.model.Restaurant
import com.example.mvvmwithhcleanarchitecture.domain.usecase.GetRestaurantListUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getRestaurantListUseCase: GetRestaurantListUseCase,
    private val mainRouter: MainRouter
) {
    val disposables = CompositeDisposable()
    val progressVisible = ObservableBoolean()
    val restaurantList = ObservableArrayList<Restaurant>()

    companion object {
        const val LATITUDE = 37.42270
        const val LONGITUDE = -122.139956
    }

    fun bound() {
        getRestaurantListUseCase.execute(LATITUDE, LONGITUDE, 0, 50)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { handleGetRestaurantListResult(it) }
            .addTo(disposables)
    }

    fun unbound() {
        disposables.clear()
    }

    private fun handleGetRestaurantListResult(result: GetRestaurantListUseCase.Result) {
        progressVisible.set(result == GetRestaurantListUseCase.Result.loading)
        when(result) {
            is GetRestaurantListUseCase.Result.Success -> {
                restaurantList.addAll(result.restaurants)
            }
            is GetRestaurantListUseCase.Result.Failure -> {
                result.throwable.localizedMessage
            }
        }
    }

    fun onRestaurantClicked(restaurant: Any) {
        mainRouter.navigate(MainRouter.Route.RESTAURANT_DETAIL, Bundle().apply {
            putInt(RestaurantDetailActivity.EXTRA_RESTAURANT_ID, (restaurant as Restaurant).id)
        })
    }

    fun onRestaurantImageClicked(restaurant: Any) {
        mainRouter.navigate(MainRouter.Route.IMAGE_DETAIL, Bundle().apply {
            putString(ImageDetailActivity.EXTRA_URL, (restaurant as Restaurant).coverImgUrl)
        })
    }
}