package com.magdalena.movietoday.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_movie")
data class FavoriteMovie(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    val id: Int? = null,

    @ColumnInfo
    val movieId: Long
)