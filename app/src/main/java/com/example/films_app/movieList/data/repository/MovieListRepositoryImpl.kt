package com.example.films_app.movieList.data.repository

import coil.network.HttpException
import com.example.films_app.movieList.data.local.MovieDatabase
import com.example.films_app.movieList.data.mappers.toLocalMovie
import com.example.films_app.movieList.data.mappers.toMovie
import com.example.films_app.movieList.data.remote.MovieApiService
import com.example.films_app.movieList.domain.Movie
import com.example.films_app.movieList.domain.repository.MovieListRepository
import com.example.films_app.movieList.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import javax.inject.Inject

class MovieListRepositoryImpl @Inject constructor(
    private val movieApiService: MovieApiService ,
    private val movieDatabase: MovieDatabase
) : MovieListRepository {

    override suspend fun getMovieList(
        forceFetchFromRemote: Boolean,
        category: String,
        page: Int
    ): Flow<Resource<List<Movie>>> {
        // return a sequence of Actions
        return flow {
            emit(Resource.Loading(true))
            val localMovieList = movieDatabase.movieDao.getMovieListByCategory(category)

            if (localMovieList.isNotEmpty() && ! forceFetchFromRemote){
                emit(Resource.Success(
                    data = localMovieList.map { localMovie ->
                        localMovie.toMovie(category)
                    })
                )

                emit(Resource.Loading(false))
                return@flow
            }

            val movieListFromApiService= try {
                movieApiService.getMoviesList(category ,page)
            } catch (e : IOException){
                e.printStackTrace()
                emit(Resource.Error(message = "Loading Error"))
                return@flow
            } catch (e : HttpException){
                e.printStackTrace()
                emit(Resource.Error(message = "Loading Error"))
                return@flow
            } catch (e : Exception){
                e.printStackTrace()
                emit(Resource.Error(message = "Loading Error"))
                return@flow
            }

            val localMovies =movieListFromApiService.results.let {
                it.map { movieDto ->
                    movieDto.toLocalMovie(category)
                }
            }

            movieDatabase.movieDao.upsertMovieList(localMovies)
            emit(Resource.Success(
                localMovies.map { it.toMovie(category) }
            ))
            emit(Resource.Loading(false))

        }
    }

    override suspend fun getMovie(id: Int): Flow<Resource<Movie>> {
        return flow {
            emit(Resource.Loading(true))
             val localMovie =movieDatabase.movieDao.getMovieById(id)
            if (localMovie != null){
                emit(Resource.Success(localMovie.toMovie(localMovie.category)))
                emit(Resource.Loading(false))
                return@flow
            }
            emit(Resource.Error(message = "No Movie"))
            emit(Resource.Loading(false))
        }
    }
}