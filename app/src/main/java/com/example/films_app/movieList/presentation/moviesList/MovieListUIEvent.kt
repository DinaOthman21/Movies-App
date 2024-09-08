package com.example.films_app.movieList.presentation.moviesList

sealed interface MovieListUIEvent {

    data class Paginate( val category :String) :MovieListUIEvent
    object Navigate : MovieListUIEvent

}