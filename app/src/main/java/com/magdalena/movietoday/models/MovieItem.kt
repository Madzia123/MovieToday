package com.magdalena.movietoday.models

import com.magdalena.movietoday.api.movie.Result

data class MovieItem(
    val movie: Result,
    var isFavorite: Boolean = false
)