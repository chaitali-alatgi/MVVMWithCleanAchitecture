package com.example.mvvmwithhcleanarchitecture.app.presentation.main

import android.os.Bundle
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
/*import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView*/
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmwithhcleanarchitecture.R
import com.example.mvvmwithhcleanarchitecture.app.presentation.BaseActivity
import com.example.mvvmwithhcleanarchitecture.app.presentation.adapter.RestaurantListAdapter
import com.example.mvvmwithhcleanarchitecture.databinding.ActivityMainBinding
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

class MainActivity: BaseActivity() {

    @Inject lateinit var viewModel: MainViewModel
    private val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        screenComponent.inject(this)
        binding.viewModel = viewModel
        viewModel.bound()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        disposable.clear()
        super.onPause()
    }

    override fun onDestroy() {
        viewModel.unbound()
        super.onDestroy()
    }

    companion object {
        @JvmStatic
        @BindingAdapter("adapter")
        fun bindList(recyclerView: RecyclerView, viewModel: MainViewModel) {
            val adapter =
                RestaurantListAdapter(
                    viewModel.restaurantList
                )
            adapter.onItemClickListener = {
                viewModel.onRestaurantClicked(it)
            }
            adapter.onImageClickedListener = {
                viewModel.onRestaurantImageClicked(it)
            }
            recyclerView.addItemDecoration(DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL))
            recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
            recyclerView.adapter = adapter

        }
    }
}