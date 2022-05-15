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

class ListRepository @Inject constructor(
    private val movieApi: MoviesApi,
    private val movieDao: MovieDao
) {
    private var isOnline: Boolean = Config.isOnline
    var movieList: List<Movie> = Collections.emptyList()

    fun getAllMovies() {
        if (isOnline) {
            val movieCall: Call<MovieListApiResponseModel> = movieApi.getAllMovies(
                api_key = Config.API_KEY,
                language = "hu-HU",
                include_adult = false,
                page = 1,
                query = "2022"
            )

            movieCall.enqueue(object : Callback<MovieListApiResponseModel> {
                override fun onFailure(call: Call<MovieListApiResponseModel>, t: Throwable) {
                    //TODO set a textField.text = t.message
                }

                override fun onResponse(call: Call<MovieListApiResponseModel>, response: Response<MovieListApiResponseModel>) {
                    val responseData = response.body()
                    val movieListResult: List<Movie>? = responseData?.results
                    if(movieListResult != null){
                        insertMoviesListToDb(movieListResult)
                        movieList = movieListResult
                    } else {
                        movieList = Collections.emptyList()
                    }
                }
            })
        } else {
            movieList = movieDao.getAllMovies()
        }
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


    fun insertMoviesList(movies: List<Movie>): List<Long> {
        if (isOnline) {
            movieApi.insertMoviesList(movies)
        }
        return insertMoviesListToDb(movies)
    }

    fun insertMoviesListToDb(movies: List<Movie>): List<Long> {
        return movieDao.insertMoviesList(movies)
    }

    fun deleteAllMovies() {
        if (isOnline) {
            movieApi.deleteAllMovies()
        }
        return movieDao.deleteAllMovies()
    }
}
