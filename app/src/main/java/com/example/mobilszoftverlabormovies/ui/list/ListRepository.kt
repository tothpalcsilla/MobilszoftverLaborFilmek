package com.example.mobilszoftverlabormovies.ui.list

import androidx.annotation.WorkerThread
import com.example.mobilszoftverlabormovies.Config
import com.example.mobilszoftverlabormovies.database.MovieDao
import com.example.mobilszoftverlabormovies.model.Movie
import com.example.mobilszoftverlabormovies.model.network.MovieListApiResponseModel
import com.example.mobilszoftverlabormovies.network.MoviesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
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

    @WorkerThread
    fun loadMovies(
        onStart: () -> Unit,
        onCompletion: () -> Unit,
        onError: (String) -> Unit
    ) = flow {
        movieList = movieDao.getAllMovies()
        if (movieList.isEmpty() && isOnline) {
            val response = movieApi.getMovies(
                api_key = Config.API_KEY,
                language = Config.LANGUAGE,
                include_adult = false,
                page = 1,
                query = "barbie"
            )
            response.enqueue(object : Callback<MovieListApiResponseModel> {
                override fun onFailure(call: Call<MovieListApiResponseModel>, t: Throwable) {
                    movieList = Collections.emptyList()
                }

                override fun onResponse(
                    call: Call<MovieListApiResponseModel>,
                    response: Response<MovieListApiResponseModel>
                ) {
                    val responseData = response.body()
                    val movieListResult: List<Movie>? = responseData?.results
                    movieList = if (movieListResult != null) {
                        insertMoviesListToDb(movieListResult)
                        movieListResult
                    } else {
                        Collections.emptyList()
                    }
                }
            })
        }
        emit(movieList)
    }.onStart { onStart() }.onCompletion {
        onCompletion()
    }.flowOn(Dispatchers.IO)

    fun getLatestMovies(): List<Movie> {
        val movieList: List<Movie> = if (isOnline) {
            movieApi.getLatestMovies(api_key = Config.API_KEY, language = Config.LANGUAGE, page = 1)
        } else {
            movieDao.getLatestMovies()
        }
        insertMoviesListToDb(movieList)
        return movieList
    }

    fun getPopularMovies(): List<Movie> {
        val movieList: List<Movie> = if (isOnline) {
            movieApi.getPopularMovies(api_key = Config.API_KEY, language = Config.LANGUAGE, page = 1)
        } else {
            movieDao.getPopularMovies()
        }
        insertMoviesListToDb(movieList)
        return movieList
    }

    fun getNowPlayingMovies(): List<Movie> {
        val movieList: List<Movie> = if (isOnline) {
            movieApi.getNowPlayingMovies(api_key = Config.API_KEY, language = Config.LANGUAGE, page = 1)
        } else {
            movieDao.getNowPlayingMovies()
        }
        insertMoviesListToDb(movieList)
        return movieList
    }

    fun insertMoviesListToDb(movies: List<Movie>): List<Long> {
        return movieDao.insertMoviesList(movies)
    }

    fun deleteAllMoviesFromDb() {
        return movieDao.deleteAllMovies()
    }
}
