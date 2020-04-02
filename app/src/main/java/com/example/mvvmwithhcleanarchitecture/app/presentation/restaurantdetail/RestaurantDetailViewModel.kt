package com.example.mvvmwithhcleanarchitecture.app.presentation.restaurantdetail

import android.os.Bundle
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.example.mvvmwithhcleanarchitecture.app.ext.addTo
import com.example.mvvmwithhcleanarchitecture.app.presentation.imagedetail.ImageDetailActivity
import com.example.mvvmwithhcleanarchitecture.domain.usecase.GetRestaurantListUseCase
import com.example.mvvmwithhcleanarchitecture.domain.usecase.GetRestaurantUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class RestaurantDetailViewModel @Inject constructor(
    private val getRestaurantUseCase: GetRestaurantUseCase,
    private val restaurantDetailRouter: RestaurantDetailRouter
) {
    private val disposables = CompositeDisposable()
    val progressVisible = ObservableBoolean()
    var restaurantName = ObservableField<String>()
    var restaurantStatus = ObservableField<String>()
    var restaurantDescription = ObservableField<String>()
    var restaurantImage = ObservableField<String>()

    fun bound(id: Int) {
        getRestaurantUseCase.execute(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { handleGetRestaurantListResult(it) }
            .addTo(disposables)
    }

    private fun handleGetRestaurantListResult(result: GetRestaurantUseCase.Result?) {
        progressVisible.set(result == GetRestaurantUseCase.Result.loading)
        when(result) {
            is GetRestaurantUseCase.Result.Success -> {
                restaurantName.set(result.restaurant.name)
                restaurantDescription.set(result.restaurant.description)
                restaurantStatus.set(result.restaurant.status)
                restaurantImage.set(result.restaurant.coverImgUrl)
            }
            is GetRestaurantUseCase.Result.Failure -> {

            }
        }
    }

    fun onImageClicked() {
        restaurantDetailRouter.navigate(RestaurantDetailRouter.Route.IMAGE_DETAIL, Bundle().apply {
            putString(ImageDetailActivity.EXTRA_URL, restaurantImage.get())
        })
    }
    fun unbound() {
        disposables.clear()
    }
}