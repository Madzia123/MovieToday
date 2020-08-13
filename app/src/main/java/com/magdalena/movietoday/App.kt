package com.magdalena.movietoday

import android.app.Application
import androidx.multidex.MultiDexApplication
import com.magdalena.movietoday.di.AppComponent
import com.magdalena.movietoday.di.ContextModule
import com.magdalena.movietoday.di.DaggerAppComponent

class App : MultiDexApplication() {

    companion object {
        lateinit var injector: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        injector = DaggerAppComponent
            .builder()
            .contextModule(ContextModule(this))
            .build()
        injector.inject(this)

    }

}