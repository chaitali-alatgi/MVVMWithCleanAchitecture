package com.example.mvvmwithhcleanarchitecture.app.presentation.restaurantdetail

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.mvvmwithhcleanarchitecture.R
import com.example.mvvmwithhcleanarchitecture.app.presentation.BaseActivity
import com.example.mvvmwithhcleanarchitecture.databinding.ActivityRestaurantDetailBinding
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

class RestaurantDetailActivity: BaseActivity() {

    @Inject lateinit var viewModel: RestaurantDetailViewModel
    private val disposables = CompositeDisposable()

    companion object {
        const val EXTRA_RESTAURANT_ID = "restaurant_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityRestaurantDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_restaurant_detail)

        screenComponent.inject(this)
        binding.viewModel = viewModel
        viewModel.bound(intent.getIntExtra(EXTRA_RESTAURANT_ID, -1))
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        disposables.clear()
        super.onPause()
    }

    override fun onDestroy() {
        viewModel.unbound()
        super.onDestroy()
    }
}