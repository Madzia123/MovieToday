package com.magdalena.movietoday.di

import com.magdalena.movietoday.App
import com.magdalena.movietoday.App.Companion.injector
import com.magdalena.movietoday.presentation.movieDetails.MovieDetailsViewModel
import com.magdalena.movietoday.presentation.movieList.MovieListViewModel
import dagger.Component

import javax.inject.Singleton

@Singleton
@Component(modules = [RoomModule::class])
interface AppComponent {

    fun inject(movieDetailsViewModel: MovieDetailsViewModel)
    fun inject(movieListViewModel: MovieListViewModel)
    fun inject(app: App)


    @Component.Builder
    interface Builder {
        fun build(): AppComponent
    }

}