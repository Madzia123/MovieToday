package com.magdalena.movietoday.di

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.magdalena.movietoday.App.Companion.injector
import com.magdalena.movietoday.presentation.movieDetails.MovieDetailsViewModel
import com.magdalena.movietoday.presentation.movieList.MovieListViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseViewModel : ViewModel() {


    val disposables = CompositeDisposable()

    var navigate: NavController? = null


    init {
        inject()
    }

    private fun inject() {
        when (this) {
            is MovieListViewModel -> injector.inject(this)
            is MovieDetailsViewModel -> injector.inject(this)
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

}