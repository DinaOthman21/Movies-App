package com.example.films_app.movieList.data.remote


import com.example.films_app.movieList.util.Constants
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {

    @GET("movie/{category}")
    suspend fun getMoviesList(
        @Path("category") category : String ,

        //Query parameters
        @Query("page") page :Int ,
        @Query("api_key") api_key :String = Constants.API_KEY
    )


}