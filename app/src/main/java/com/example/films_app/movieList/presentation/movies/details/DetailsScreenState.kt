package com.example.films_app.movieList.presentation.movies.details

import com.example.films_app.movieList.domain.Movie

data class DetailsScreenState(
    val isLoading: Boolean = false ,
    val movie :Movie? = null
)
