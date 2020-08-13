package com.magdalena.movietoday.manager

import com.magdalena.movietoday.database.FavoriteMovie
import com.magdalena.movietoday.database.FavoriteMovieDao

class FavoriteMovieManger(private val dao: FavoriteMovieDao) {

    fun saveFavoriteMovie(favoriteMovie: FavoriteMovie){
        dao.saveFavoriteMovie(favoriteMovie)
    }

    fun deleteFavoriteMovie(movieId:Long){
        dao.deleteFavoriteMovie(movieId)
    }
}