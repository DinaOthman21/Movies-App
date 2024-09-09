package com.example.films_app.movieList.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Movie
import androidx.compose.material.icons.rounded.Upcoming
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.films_app.R
import com.example.films_app.movieList.presentation.movies.moviesList.MovieListUIEvent
import com.example.films_app.movieList.presentation.navigation.Screen


@Composable
fun BottomNavigationBar(
    bottomNavController : NavHostController,
    onEvent :(MovieListUIEvent) -> Unit
){

    val items = listOf(
        BottomItem( title = stringResource(id = R.string.popular) , icon = Icons.Rounded.Movie) ,
        BottomItem(  title = stringResource(id = R.string.upcoming) , icon = Icons.Rounded.Upcoming )
    )

    val selected = rememberSaveable {
        mutableIntStateOf(0)
    }
//onSurfaceVariant
    NavigationBar {
        Row(
            modifier = Modifier.background(MaterialTheme.colorScheme.onTertiary)
        ) {
            items.forEachIndexed { index, bottomItem ->
                NavigationBarItem(
                    selected = selected.intValue ==index,
                    onClick = {
                        selected.intValue=index
                        when(selected.intValue){
                            0->{
                                onEvent(MovieListUIEvent.Navigate)
                                bottomNavController.popBackStack()
                                bottomNavController.navigate(Screen.PopularMovies.route)
                            }
                            1->{
                                onEvent(MovieListUIEvent.Navigate)
                                bottomNavController.popBackStack()
                                bottomNavController.navigate(Screen.UpComingMovies.route)
                            }
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = bottomItem.icon,
                            contentDescription = bottomItem.title ,
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    } ,
                    label = {
                        Text(
                            text = bottomItem.title,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                )
            }

        }
    }
}
