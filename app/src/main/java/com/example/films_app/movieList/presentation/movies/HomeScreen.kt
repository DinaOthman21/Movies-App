package com.example.films_app.movieList.presentation.movies



import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.films_app.R
import com.example.films_app.movieList.presentation.common.BottomNavigationBar
import com.example.films_app.movieList.presentation.movies.moviesList.MovieListViewModel
import com.example.films_app.movieList.presentation.movies.moviesList.PopularMoviesScreen
import com.example.films_app.movieList.presentation.movies.moviesList.UpcomingMoviesScreen
import com.example.films_app.movieList.presentation.navigation.Screen



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController ,
    movieListViewModel : MovieListViewModel
){

    val movieListState =movieListViewModel.movieListState.collectAsState().value
    val bottomNavController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                bottomNavController =bottomNavController ,
               onScreenChange ={ isPopularScreen ->
                       movieListViewModel.toggleScreen()
                       bottomNavController.navigate(
                           if (isPopularScreen) Screen.PopularMovies.route
                           else Screen.UpComingMovies.route
                       )
               }
            )
        } ,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = if (movieListState.isCurrentPopularScreen) stringResource(id = R.string.popularMovies)
                        else stringResource(id = R.string.upcomingMovies) ,
                        fontSize = 20.sp
                    )
                } ,
                modifier = Modifier.shadow(2.dp) ,
                colors = TopAppBarDefaults.mediumTopAppBarColors(MaterialTheme.colorScheme.onTertiary)
            )

        }
    ) { paddingValues ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)) {
            NavHost(navController = bottomNavController, startDestination =Screen.PopularMovies.route ) {
                composable(Screen.PopularMovies.route){
                    PopularMoviesScreen(
                        navController=navController ,
                        movieListState = movieListState,
                        onPaginate = {
                            movieListViewModel.paginatePopularMovies()
                        }
                    )
                }
                composable(Screen.UpComingMovies.route){
                    UpcomingMoviesScreen(
                        navController=navController ,
                        movieListState = movieListState,
                        onPaginate = {
                            movieListViewModel.paginateUpcomingMovies()
                        }
                    )
                }
            }
        }
    }


}



