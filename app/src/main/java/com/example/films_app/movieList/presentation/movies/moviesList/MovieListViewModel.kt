package com.example.films_app.movieList.presentation.movies.moviesList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.films_app.movieList.domain.repository.MovieListRepository
import com.example.films_app.movieList.util.Constants
import com.example.films_app.movieList.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/*
@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val movieListRepository: MovieListRepository
) : ViewModel() {

    private var _movieListState = MutableStateFlow(MovieListState())
    val movieListState =_movieListState.asStateFlow()

    init {
        getPopularMovieList(false)
        getUpComingMovieList(false)
    }



    fun onEvent(event : MovieListUIEvent){
        when(event){
            MovieListUIEvent.Navigate -> {
                _movieListState.update {
                    it.copy(isCurrentPopularScreen = !movieListState.value.isCurrentPopularScreen)
                }
            }
            is MovieListUIEvent.Paginate ->{
                if(event.category== Constants.POPULAR) {
                    getPopularMovieList(true)
                }
                else if (event.category == Constants.UPCOMING){
                    getUpComingMovieList(true)
                }
            }
        }

    }



    private fun getPopularMovieList(forceFetchFromRemote : Boolean){
        viewModelScope.launch {
            _movieListState.update {
                it.copy(isLoading = true)
            }
            movieListRepository.getMovieList(
                forceFetchFromRemote = forceFetchFromRemote ,
                category = Constants.POPULAR,
                page = movieListState.value.popularMovieListPage
            ).collectLatest { result ->
                when(result){
                    is Resource.Error -> {
                        _movieListState.update {
                            it.copy(isLoading = false)
                        }
                    }
                    is Resource.Loading -> {
                        _movieListState.update {
                            it.copy(isLoading = result.isLoading)
                        }
                    }
                    is Resource.Success -> {
                        result.data?.let { popularList ->
                            _movieListState.update {
                                it.copy(
                                    popularMovieList = movieListState.value.popularMovieList+popularList.shuffled() ,
                                    popularMovieListPage = movieListState.value.popularMovieListPage+1
                                )
                            }

                        }
                    }
                }

            }
        }

    }



    private fun getUpComingMovieList(forceFetchFromRemote : Boolean){
        viewModelScope.launch {
            _movieListState.update {
                it.copy(isLoading = true)
            }
            movieListRepository.getMovieList(
                forceFetchFromRemote = forceFetchFromRemote ,
                category = Constants.UPCOMING,
                page = movieListState.value.upcomingMovieListPage
            ).collectLatest { result ->
                when(result){
                    is Resource.Error -> {
                        _movieListState.update {
                            it.copy(isLoading = false)
                        }
                    }
                    is Resource.Loading -> {
                        _movieListState.update {
                            it.copy(isLoading = result.isLoading)
                        }
                    }
                    is Resource.Success -> {
                        result.data?.let { upcomingList ->
                            _movieListState.update {
                                it.copy(
                                    upcomingMovieList = movieListState.value.upcomingMovieList + upcomingList.shuffled() ,
                                    upcomingMovieListPage = movieListState.value.upcomingMovieListPage+1
                                )
                            }

                        }
                    }
                }

            }
        }

    }









}*/

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val movieListRepository: MovieListRepository
) : ViewModel() {

    private var _movieListState = MutableStateFlow(MovieListState())
    val movieListState = _movieListState.asStateFlow()

    init {
        getPopularMovieList(false)
        getUpComingMovieList(false)
    }


    fun toggleScreen() {
        _movieListState.update {
            it.copy(isCurrentPopularScreen = !movieListState.value.isCurrentPopularScreen)
        }
    }


    fun paginatePopularMovies() {
        getPopularMovieList(true)
    }


    fun paginateUpcomingMovies() {
        getUpComingMovieList(true)
    }

    private fun getPopularMovieList(forceFetchFromRemote: Boolean) {
        viewModelScope.launch {
            _movieListState.update {
                it.copy(isLoading = true)
            }
            movieListRepository.getMovieList(
                forceFetchFromRemote = forceFetchFromRemote ,
                category = Constants.POPULAR,
                page = movieListState.value.popularMovieListPage
            ).collectLatest { result ->
                when(result){
                    is Resource.Error -> {
                        _movieListState.update {
                            it.copy(isLoading = false)
                        }
                    }
                    is Resource.Loading -> {
                        _movieListState.update {
                            it.copy(isLoading = result.isLoading)
                        }
                    }
                    is Resource.Success -> {
                        result.data?.let { popularList ->
                            _movieListState.update {
                                it.copy(
                                    popularMovieList = movieListState.value.popularMovieList+popularList.shuffled() ,
                                    popularMovieListPage = movieListState.value.popularMovieListPage+1
                                )
                            }

                        }
                    }
                }

            }
        }
    }

    private fun getUpComingMovieList(forceFetchFromRemote: Boolean) {
        viewModelScope.launch {
            _movieListState.update {
                it.copy(isLoading = true)
            }
            movieListRepository.getMovieList(
                forceFetchFromRemote = forceFetchFromRemote ,
                category = Constants.UPCOMING,
                page = movieListState.value.upcomingMovieListPage
            ).collectLatest { result ->
                when(result){
                    is Resource.Error -> {
                        _movieListState.update {
                            it.copy(isLoading = false)
                        }
                    }
                    is Resource.Loading -> {
                        _movieListState.update {
                            it.copy(isLoading = result.isLoading)
                        }
                    }
                    is Resource.Success -> {
                        result.data?.let { upcomingList ->
                            _movieListState.update {
                                it.copy(
                                    upcomingMovieList = movieListState.value.upcomingMovieList + upcomingList.shuffled() ,
                                    upcomingMovieListPage = movieListState.value.upcomingMovieListPage+1
                                )
                            }

                        }
                    }
                }

            }
        }
    }
}
