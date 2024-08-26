package com.example.films_app.movieList.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [LocalMovie::class] ,
    version = 1 ,
    exportSchema = false
)
abstract class MovieDatabase : RoomDatabase() {
    abstract val movieDao : MovieDao
}