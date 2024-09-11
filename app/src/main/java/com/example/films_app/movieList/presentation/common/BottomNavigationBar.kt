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
import com.example.films_app.movieList.presentation.navigation.Screen


@Composable
fun BottomNavigationBar(
    bottomNavController : NavHostController,
    onScreenChange: (Boolean) -> Unit
){

    val items = listOf(
        BottomItem( title = stringResource(id = R.string.popular) , icon = Icons.Rounded.Movie) ,
        BottomItem(  title = stringResource(id = R.string.upcoming) , icon = Icons.Rounded.Upcoming )
    )

    val selected = rememberSaveable { mutableIntStateOf(0) }

    NavigationBar {
        Row(
            modifier = Modifier.background(MaterialTheme.colorScheme.onSecondary)
        ) {
            items.forEachIndexed { index, bottomItem ->
                NavigationBarItem(
                    selected = selected.intValue ==index,
                    onClick = {
                        if (selected.intValue != index) {
                            selected.intValue = index
                            onScreenChange(index == 0)
                            bottomNavController.popBackStack()
                            bottomNavController.navigate(
                                if (index == 0) Screen.PopularMovies.route
                                else Screen.UpComingMovies.route
                            )
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
