package com.example.mobilszoftverlabormovies.ui.list

import androidx.annotation.WorkerThread
import com.example.mobilszoftverlabormovies.Config
import com.example.mobilszoftverlabormovies.database.MovieDao
import com.example.mobilszoftverlabormovies.model.Movie
import com.example.mobilszoftverlabormovies.model.network.MovieListApiResponseModel
import com.example.mobilszoftverlabormovies.network.MoviesApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import javax.inject.Inject

class DetailRepository @Inject constructor(
    private val movieApi: MoviesApi,
    private val movieDao: MovieDao
) {

    private var isOnline: Boolean = Config.isOnline

    fun getMovie(movie_id: String): Movie {
        val movie: Movie = if (isOnline) {
            movieApi.getMovie(movie_id= movie_id, language = Config.LANGUAGE)
        } else {
            movieDao.getMovie(movie_id)
        }
        insertMovieToDb(movie)
        return movie
    }

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
