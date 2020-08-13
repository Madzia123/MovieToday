package com.magdalena.movietoday.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoriteMovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveFavoriteMovie(favoriteMovie: FavoriteMovie)

    @Query("DELETE FROM favorite_movie WHERE movieId = :movieId")
    fun deleteFavoriteMovie(movieId: Long)

    @Query("SELECT * FROM favorite_movie")
    fun getFavoriteMovieIds(): MutableList<FavoriteMovie>

    @Query("SELECT * FROM favorite_movie WHERE movieId = :movieId LIMIT 1")
    fun getFavoriteMovie(movieId: Int): LiveData<FavoriteMovie?>

}