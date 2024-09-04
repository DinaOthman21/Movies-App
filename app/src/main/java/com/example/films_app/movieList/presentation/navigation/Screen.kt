package com.example.films_app.movieList.presentation.navigation

sealed class Screen (val route :String){

    data object Home : Screen("HomeScreen")
    data object PopularMovies : Screen("PopularMoviesScreen")
    data object UpComingMovies : Screen("UpComingMoviesScreen")
    data object Details : Screen("DetailsScreen")

}