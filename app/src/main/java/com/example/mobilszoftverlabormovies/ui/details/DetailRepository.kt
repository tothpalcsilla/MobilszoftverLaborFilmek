package com.example.mobilszoftverlabormovies.ui.list

import com.example.mobilszoftverlabormovies.Config
import com.example.mobilszoftverlabormovies.database.MovieDao
import com.example.mobilszoftverlabormovies.model.Movie
import com.example.mobilszoftverlabormovies.network.MoviesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DetailRepository @Inject constructor(
    private val movieApi: MoviesApi,
    private val movieDao: MovieDao
) {

    private var isOnline: Boolean = Config.isOnline

    fun getMovie(movie_id: Long) = flow {
        emit(movieDao.getMovie(movie_id))
    }.flowOn(Dispatchers.IO)

    // CREATE
    fun insertMovie(movie: Movie): Long {
        if (isOnline) {
            movieApi.insertMovie(movie)
        }
        return insertMovieToDb(movie)
    }

    private fun insertMovieToDb(movie: Movie): Long {
        return movieDao.insertMovie(movie)
    }

    // POST
    fun updateMovie(movie: Movie) {
        if (isOnline) {
            movieApi.updateMovie(movie)
        }
        return movieDao.updateMovie(movie)
    }

    // DELETE
    fun deleteMovie(movie: Movie) {
        if (isOnline) {
            movieApi.deleteMovie(movie)
        }
        return movieDao.deleteMovie(movie)
    }
}
