package com.magdalena.movietoday.network

import com.magdalena.movietoday.api.movie.MovieResponse
import com.magdalena.movietoday.api.movieDetails.MovieDetailsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("/movie/now_playing")
    fun getMoviePlaying(): Single<MovieResponse>

    @GET("/search/movie")
    fun searchMovies(@Query("query") query: String): Single<MovieResponse>

    @GET("/movie/{movie_id}")
    fun getMovieDetailsId(@Path("movie_id") movieId:Int) : Single<MovieDetailsResponse>

}