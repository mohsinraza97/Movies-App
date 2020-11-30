package com.wajahatkarim.movies.swvl

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.wajahatkarim.movies.swvl.di.components.DaggerAppComponent
import com.wajahatkarim.movies.swvl.utils.isNight
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class SwvlApp : Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>
    
    override fun onCreate() {
        super.onCreate()
        initDi()

        setupDayNightMode()
    }

    fun initDi() {
        DaggerAppComponent.builder()
            .create(this)
            .build()
            .inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }

    fun setupDayNightMode() {
        // Get UI mode and set
        val mode = if (isNight()) {
            AppCompatDelegate.MODE_NIGHT_YES
        } else {
            AppCompatDelegate.MODE_NIGHT_NO
        }

        AppCompatDelegate.setDefaultNightMode(mode)
    }
}