package com.example.mobilszoftverlabormovies.ui.list

import android.net.NetworkCapabilities
import android.os.Build
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

    fun isOnline(): Boolean {
        val connectivityManager = Config.connectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else {
            @Suppress("DEPRECATION")
            val networkInfo = connectivityManager.activeNetworkInfo ?: return false

            @Suppress("DEPRECATION")
            return networkInfo.isConnectedOrConnecting
        }
    }

    fun getMovie(movie_id: Long) = flow {
        emit(movieDao.getMovie(movie_id))
    }.flowOn(Dispatchers.IO)

    // CREATE
    fun insertMovie(movie: Movie): Long {
        if (isOnline()) {
            movieApi.insertMovie(movie)
        }
        return insertMovieToDb(movie)
    }

    private fun insertMovieToDb(movie: Movie): Long {
        return movieDao.insertMovie(movie)
    }

    // POST
    fun updateMovie(movie: Movie) {
        if (isOnline()) {
            movieApi.updateMovie(movie)
        }
        return movieDao.updateMovie(movie)
    }

    // DELETE
    fun deleteMovie(movie: Movie) {
        if (isOnline()) {
            movieApi.deleteMovie(movie)
        }
        return movieDao.deleteMovie(movie)
    }
}
