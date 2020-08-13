package com.magdalena.movietoday.presentation.movieList

import androidx.lifecycle.MutableLiveData
import com.magdalena.movietoday.api.NetworkMessage
import com.magdalena.movietoday.api.movie.Result
import com.magdalena.movietoday.database.FavoriteMovie
import com.magdalena.movietoday.di.BaseViewModel
import com.magdalena.movietoday.manager.FavoriteMovieManger
import com.magdalena.movietoday.manager.MovieManager
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import javax.inject.Inject

class MovieListViewModel : BaseViewModel() {

    @Inject
    lateinit var movieManager: MovieManager

    @Inject
    lateinit var favoriteMovieManger: FavoriteMovieManger

    var progressShow: MutableLiveData<Boolean> = MutableLiveData()
    val errorMessage: MutableLiveData<String> = MutableLiveData()
    val movies: MutableLiveData<MutableList<Result>> = MutableLiveData()
    val favoriteMovies: MutableLiveData<MutableList<FavoriteMovie>> = MutableLiveData()


    init {
        getNowMovies()
    }

    private fun getNowMovies() {
        disposables.add(
            movieManager.nowPlayingMovie()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    progressShow.value = true
                }.doOnTerminate {
                    progressShow.value = false
                }
                .subscribe({
                    movies.postValue(it.results)
                }, {

                    if (it is HttpException) {
                        errorMessage.value =
                            NetworkMessage.getErrorMessage(it.response()?.errorBody())
                    }
                }
                )
        )
    }

    fun searchMovie(searchMovie: String) {
        disposables.add(
            movieManager.searchMovies(searchMovie)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    progressShow.value = true
                }.doOnTerminate {
                    progressShow.value = false
                }
                .subscribe({
                    movies.postValue(it.results)
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
                .doOnSubscribe {
                    progressShow.value = true
                }.doOnTerminate {
                    progressShow.value = false
                }
                .subscribe({

                }, {


                }
                )
        )
    }

    fun deleteFavoriteMovie(movieId: Int) {
        disposables.add(
            Observable.fromCallable {
                favoriteMovieManger.deleteFavoriteMovie(movieId)
            }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    progressShow.value = true
                }.doOnTerminate {
                    progressShow.value = false
                }
                .subscribe({

                }, {


                }
                )
        )
    }

    fun getFavoriteMovies() {
        disposables.add(
            Observable.fromCallable {
                favoriteMovieManger.getFavoriteMovieId()
            }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    progressShow.value = true
                }.doOnTerminate {
                    progressShow.value = false
                }
                .subscribe({
                    favoriteMovies.postValue(it)
                }, {


                }
                )
        )
    }

}