package com.magdalena.movietoday.models

import com.magdalena.movietoday.api.nowPlaying.Result

data class MovieItem(
    val movie: Result,
    var isFavorite: Boolean = false
)