package com.magdalena.movietoday.manager

import com.magdalena.movietoday.api.nowPlaying.NowPlayingResponse
import com.magdalena.movietoday.network.MovieApi
import io.reactivex.Single

class MovieManager(private val api: MovieApi) {

    fun nowPlayingMovie(): Single<NowPlayingResponse> = api.getMoviePlaying()


}