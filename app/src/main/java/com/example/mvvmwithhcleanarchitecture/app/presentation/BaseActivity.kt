package com.example.mvvmwithhcleanarchitecture.app.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mvvmwithhcleanarchitecture.BaseApplication
import com.example.mvvmwithhcleanarchitecture.R
import com.example.mvvmwithhcleanarchitecture.app.di.application.ApplicationComponent
import com.example.mvvmwithhcleanarchitecture.app.di.screen.ScreenModule

open class BaseActivity : AppCompatActivity() {

   val screenComponent by lazy {
        getApplicationComponent().plus(ScreenModule(this))
   }
    private fun getApplicationComponent(): ApplicationComponent {
        return (application as BaseApplication).component
    }
}
