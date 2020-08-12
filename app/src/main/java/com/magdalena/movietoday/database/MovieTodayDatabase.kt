package com.magdalena.movietoday.database

import androidx.room.Database
import androidx.room.RoomDatabase
@Database(entities = [FavoriteMovie::class], version = 1, exportSchema = false)
abstract class MovieTodayDatabase : RoomDatabase(){
    abstract fun favoriteMovieDao(): FavoriteMovieDao
}