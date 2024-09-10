package com.example.films_app.movieList.presentation.movies.details


import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.films_app.movieList.domain.repository.MovieListRepository
import com.example.films_app.movieList.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsScreenViewModel @Inject constructor(
    private val movieListRepository: MovieListRepository,
    private val savedStateHandle: SavedStateHandle
):ViewModel(){

    private val movieId = savedStateHandle.get<Int>("movieId")

    private var _detailsState = MutableStateFlow(DetailsScreenState())
   val detailsState = _detailsState.asStateFlow()

    init {
        getMovie(movieId?:-1)
    }


     fun getMovie(id:Int){
        viewModelScope.launch(Dispatchers.IO) {
            _detailsState.update {
                it.copy(isLoading = true)
            }
            movieListRepository.getMovie(id).collectLatest { result->
                when(result){
                    is Resource.Error -> {
                        _detailsState.update {
                            it.copy(isLoading = false)
                        }
                    }

                    is Resource.Loading -> {
                        _detailsState.update {
                            it.copy(isLoading = result.isLoading)
                        }
                    }

                    is Resource.Success -> {
                        result.data?.let { movie->
                            _detailsState.update {
                                it.copy(movie = movie , isLoading = false)
                            }
                        }
                    }
                }

            }
        }
    }


}