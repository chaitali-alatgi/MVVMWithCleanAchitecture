package com.example.mvvmwithhcleanarchitecture.app.presentation.restaurantdetail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.example.mvvmwithhcleanarchitecture.app.presentation.imagedetail.ImageDetailActivity
import java.lang.ref.WeakReference

class RestaurantDetailRouter(private val activityRef: WeakReference<Activity>) {
    enum class Route {
        IMAGE_DETAIL,
    }

    fun navigate(route: Route, bundle: Bundle = Bundle()) {
        when(route) {
            Route.IMAGE_DETAIL -> {
                showNextScreen(ImageDetailActivity::class.java, bundle)
            }
        }
    }

    private fun showNextScreen(clazz: Class<*>, bundle: Bundle) {
        activityRef.get()?.startActivity(Intent(activityRef.get(), clazz).putExtras(bundle))    }
}
