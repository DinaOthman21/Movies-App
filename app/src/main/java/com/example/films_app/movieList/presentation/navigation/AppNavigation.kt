package com.example.films_app.movieList.presentation.navigation



import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.films_app.movieList.presentation.movies.HomeScreen
import com.example.films_app.movieList.presentation.movies.details.DetailsScreen
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
            SetBarColor(MaterialTheme.colorScheme.onSecondary)
            HomeScreen(navController = navController)
        }
        composable(
            route = Screen.Details.route + "/{movieId}",
            arguments = listOf(navArgument("movieId") { type = NavType.IntType })
        ) { backStackEntry ->
            DetailsScreen( backStackEntry)
            SetBarColor( MaterialTheme.colorScheme.background)
        }
    }
}

