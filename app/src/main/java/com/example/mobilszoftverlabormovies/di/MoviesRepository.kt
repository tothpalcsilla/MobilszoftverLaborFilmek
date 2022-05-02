package com.example.mobilszoftverlabormovies.di

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.example.mobilszoftverlabormovies.Config
import com.example.mobilszoftverlabormovies.database.MovieDao
import com.example.mobilszoftverlabormovies.model.Movie
import com.example.mobilszoftverlabormovies.network.MoviesApi
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class MoviesRepository {

    private var isOnline: Boolean = Config.isOnline
    private val movieDao: MovieDao = DatabaseModule
    private val movieApi: MoviesApi = ApiModule

    fun getAllMovies(): List<Movie> {
        val movieList: List<Movie> = if (isOnline) {
            movieApi.getAllMovies()
        } else {
            movieDao.getAllMovies()
        }
        insertMoviesListToDb(movieList)
        return movieList
    }

    fun getLatestMovies(): List<Movie> {
        val movieList: List<Movie> = if (isOnline) {
            movieApi.getLatestMovies()
        } else {
            movieDao.getLatestMovies()
        }
        insertMoviesListToDb(movieList)
        return movieList
    }

    fun getPopularMovies(): List<Movie> {
        val movieList: List<Movie> = if (isOnline) {
            movieApi.getPopularMovies()
        } else {
            movieDao.getPopularMovies()
        }
        insertMoviesListToDb(movieList)
        return movieList
    }

    fun getNowPlayingMovies(): List<Movie> {
        val movieList: List<Movie> = if (isOnline) {
            movieApi.getNowPlayingMovies()
        } else {
            movieDao.getNowPlayingMovies()
        }
        insertMoviesListToDb(movieList)
        return movieList
    }

    fun getMovie(movie_id: String): Movie {
        val movie: Movie = if (isOnline) {
            movieApi.getMovie(movie_id)
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

    fun insertMovieToDb(movie: Movie): Long {
        return movieDao.insertMovie(movie)
    }

    fun insertMoviesList(movies: List<Movie>): List<Long> {
        if (isOnline) {
            movieApi.insertMoviesList(movies)
        }
        return insertMoviesListToDb(movies)
    }

    fun insertMoviesListToDb(movies: List<Movie>): List<Long> {
        return movieDao.insertMoviesList(movies)
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

    fun deleteAllMovies() {
        if (isOnline) {
            movieApi.deleteAllMovies()
        }
        return movieDao.deleteAllMovies()
    }
}