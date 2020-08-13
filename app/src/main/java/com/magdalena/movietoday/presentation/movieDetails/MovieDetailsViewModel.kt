package com.magdalena.movietoday.presentation.movieDetails

import androidx.lifecycle.MutableLiveData
import com.magdalena.movietoday.api.NetworkMessage
import com.magdalena.movietoday.api.movie.Result
import com.magdalena.movietoday.api.movieDetails.MovieDetailsResponse
import com.magdalena.movietoday.database.FavoriteMovie
import com.magdalena.movietoday.di.BaseViewModel
import com.magdalena.movietoday.manager.FavoriteMovieManger
import com.magdalena.movietoday.manager.MovieManager
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import javax.inject.Inject

class MovieDetailsViewModel : BaseViewModel() {

    @Inject
    lateinit var movieManager: MovieManager

    @Inject
    lateinit var favoriteMovieManger: FavoriteMovieManger

    var progressShow: MutableLiveData<Boolean> = MutableLiveData()
    val errorMessage: MutableLiveData<String> = MutableLiveData()
    val result: MutableLiveData<MovieDetailsResponse> = MutableLiveData()



    fun showMovieDetails(movieId: Long) {
        disposables.add(
            movieManager.getMovieDetails(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    progressShow.value = true
                }.doOnTerminate {
                    progressShow.value = false
                }
                .subscribe({
                    result.postValue(it)
                }, {

                    if (it is HttpException) {
                        errorMessage.value =
                            NetworkMessage.getErrorMessage(it.response()?.errorBody())
                    }
                }
                )
        )
    }

    fun saveFavoriteMovie(favoriteMovie: FavoriteMovie) {
        disposables.add(
            Observable.fromCallable {
                favoriteMovieManger.saveFavoriteMovie(favoriteMovie)
            }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({

                }, {


                }
                )
        )
    }

    fun deleteFavoriteMovie(movieId: Long) {
        disposables.add(
            Observable.fromCallable {
                favoriteMovieManger.deleteFavoriteMovie(movieId)
            }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({

                }, {


                }
                )
        )
    }
}