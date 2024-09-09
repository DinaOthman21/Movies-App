package com.example.films_app.movieList.presentation.movies.moviesList

sealed interface MovieListUIEvent {

    data class Paginate( val category :String) : MovieListUIEvent
    object Navigate : MovieListUIEvent

}