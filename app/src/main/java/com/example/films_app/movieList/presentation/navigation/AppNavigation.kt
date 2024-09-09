package com.example.films_app.movieList.presentation.navigation


import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.films_app.movieList.presentation.moviesList.HomeScreen
import com.example.films_app.movieList.presentation.moviesList.MovieListViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
 fun SetBarColor(color: Color){
    val systemUiController =rememberSystemUiController()
    LaunchedEffect(key1 = color) {
        systemUiController.setSystemBarsColor(color)
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            val movieListViewModel: MovieListViewModel = hiltViewModel()
            HomeScreen(navController = navController, movieListViewModel = movieListViewModel)
        }
        composable(
            route = Screen.Details.route + "/{movieId}",
            arguments = listOf(navArgument("movieId") { type = NavType.IntType })
        ) { _ ->
            // DetailsScreen(navController)
        }
    }
}
