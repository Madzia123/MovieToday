package com.magdalena.movietoday.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.magdalena.movietoday.database.FavoriteMovieDao
import com.magdalena.movietoday.database.MovieTodayDatabase
import com.magdalena.movietoday.manager.FavoriteMovieManger
import com.magdalena.movietoday.utils.MOVIE_TODAY_DATABASE
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Singleton
    @Provides
    fun providesDatabase(context: Context): MovieTodayDatabase {
        return Room.databaseBuilder(
            context, MovieTodayDatabase::class.java,
            MOVIE_TODAY_DATABASE
        ).setJournalMode(RoomDatabase.JournalMode.TRUNCATE).build()
    }

    @Singleton
    @Provides
    fun providesFavoriteMovie(database: MovieTodayDatabase) = database.favoriteMovieDao()

    @Provides
    fun provideFavoriteMovieManger(dao: FavoriteMovieDao): FavoriteMovieManger {
        return FavoriteMovieManger(dao)
    }

}