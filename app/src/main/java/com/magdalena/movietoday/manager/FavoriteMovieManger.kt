package com.magdalena.movietoday.manager

import com.magdalena.movietoday.database.FavoriteMovie
import com.magdalena.movietoday.database.FavoriteMovieDao

class FavoriteMovieManger(val dao: FavoriteMovieDao) {

    fun saveFavoriteMovie(favoriteMovie: FavoriteMovie) {
        dao.saveFavoriteMovie(favoriteMovie)
    }

    fun deleteFavoriteMovie(movieId: Long) {
        dao.deleteFavoriteMovie(movieId)
    }

    fun getFavoriteMovie(movieId: Long){
        dao.getFavoriteMovie(movieId)
    }

    fun getFavoriteMovieId():MutableList<FavoriteMovie> = dao.getFavoriteMovieIds()
}