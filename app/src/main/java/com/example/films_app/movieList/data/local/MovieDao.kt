package com.example.films_app.movieList.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface MovieDao {

    @Upsert
    suspend fun upsertMovieList(movieList : List<LocalMovie>)

    @Query("SELECT * FROM LocalMovie WHERE id =:id")
    suspend fun getMovieById(id:Int) : LocalMovie

    @Query("SELECT * FROM LocalMovie WHERE category =:category")
    suspend fun getMovieListByCategory(category:String) : List<LocalMovie>

}