package com.magdalena.movietoday.manager

import com.magdalena.movietoday.database.FavoriteMovie
import com.magdalena.movietoday.database.FavoriteMovieDao

class FavoriteMovieManger(private val dao: FavoriteMovieDao) {

    fun saveFavoriteMovie(favoriteMovie: FavoriteMovie) {
        dao.saveFavoriteMovie(favoriteMovie)
    }

    fun deleteFavoriteMovie(movieId: Int) {
        dao.deleteFavoriteMovie(movieId)
    }

    fun getFavoriteMovieId():MutableList<FavoriteMovie> = dao.getFavoriteMovieIds()
}