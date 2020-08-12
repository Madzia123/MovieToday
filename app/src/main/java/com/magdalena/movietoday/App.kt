package com.magdalena.movietoday

import android.app.Application
import com.magdalena.movietoday.di.AppComponent

class App : Application() {

    companion object {
        lateinit var injector: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
    }

}