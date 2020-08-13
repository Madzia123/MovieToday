package com.magdalena.movietoday.manager

import com.magdalena.movietoday.api.movie.MovieResponse
import com.magdalena.movietoday.api.movieDetails.MovieDetailsResponse
import com.magdalena.movietoday.network.MovieApi
import io.reactivex.Single

class MovieManager(private val api: MovieApi) {

    fun nowPlayingMovie(): Single<MovieResponse> = api.getMoviePlaying()

    fun searchMovies(searchMovie: String): Single<MovieResponse> = api.searchMovies(searchMovie)

    fun getMovieDetails(movieId:Int) : Single<MovieDetailsResponse> = api.getMovieDetailsId(movieId)

}