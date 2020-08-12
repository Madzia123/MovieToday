package com.magdalena.movietoday

import android.app.Application
import com.magdalena.movietoday.di.AppComponent
import com.magdalena.movietoday.di.DaggerAppComponent

class App : Application() {

    companion object {
        lateinit var injector: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        injector = DaggerAppComponent
            .builder()

            .build()
        injector.inject(this)

    }

}