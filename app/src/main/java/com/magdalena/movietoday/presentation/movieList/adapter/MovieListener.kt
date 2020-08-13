package com.magdalena.movietoday.presentation.movieList.adapter

interface MovieListener {
    fun isFavoriteMovie(isFavorite:Boolean,movieId:Long)
}