package com.example.mvvmwithhcleanarchitecture.app.di.application

import com.example.mvvmwithhcleanarchitecture.BaseApplication
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: BaseApplication) {

    @Provides
    @Singleton
    fun provideApplication(): BaseApplication {
        return application
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .baseUrl("https://api.doordash.com/v2/")
            .build()
    }
}