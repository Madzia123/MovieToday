package com.magdalena.movietoday.network

import com.magdalena.movietoday.api.nowPlaying.NowPlayingResponse
import io.reactivex.Single
import retrofit2.http.GET

interface MovieApi {

    @GET("/movie/now_playing")
    fun getMoviePlaying():Single<NowPlayingResponse>


}