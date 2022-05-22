package com.example.mobilszoftverlabormovies.ui.list

import androidx.annotation.WorkerThread
import androidx.compose.runtime.State
import com.example.mobilszoftverlabormovies.Config
import com.example.mobilszoftverlabormovies.database.MovieDao
import com.example.mobilszoftverlabormovies.model.Movie
import com.example.mobilszoftverlabormovies.model.network.MovieListApiResponseModel
import com.example.mobilszoftverlabormovies.network.MoviesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
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

    fun getMovies(index: Int) {
        if (isOnline) {
            val response = when (index) {
                0 -> movieApi.getAllMovies(
                    api_key = Config.API_KEY,
                    language = Config.LANGUAGE,
                    include_adult = false,
                    page = 1,
                    query = "lion"
                ).execute()
                1 -> movieApi.getTopRatedMovies(
                    api_key = Config.API_KEY,
                    language = Config.LANGUAGE,
                    page = 1
                ).execute()
                2 -> movieApi.getPopularMovies(
                    api_key = Config.API_KEY,
                    language = Config.LANGUAGE,
                    page = 1
                ).execute()
                3 -> movieApi.getNowPlayingMovies(
                    api_key = Config.API_KEY,
                    language = Config.LANGUAGE,
                    page = 1
                ).execute()
                else -> movieApi.getAllMovies(
                    api_key = Config.API_KEY,
                    language = Config.LANGUAGE,
                    include_adult = false,
                    page = 1,
                    query = "lion"
                ).execute()
            }
            val responseData = response.body()
            val movieListResult: List<Movie>? = responseData?.results
            if (movieListResult != null && movieListResult.isNotEmpty()) {
                movieList = movieListResult
                insertMoviesListToDb(movieListResult)
            } else {
                movieList = Collections.emptyList()
            }
        }
        if (!Config.isOnline || movieList.isEmpty()) {
            movieList = when (index) {
                0 -> movieDao.getAllMovies()
                1 -> movieDao.getTopRatedMovies()
                2 -> movieDao.getPopularMovies()
                3 -> movieDao.getNowPlayingMovies()
                else -> movieDao.getAllMovies()
            }
        }
    }

    @WorkerThread
    fun loadMovies(
        menuIndex: Int,
        onStart: () -> Unit,
        onCompletion: () -> Unit,
        onError: (String) -> Unit,
    ): Flow<List<Movie>> = flow {
        getMovies(menuIndex)
        emit(movieList)
    }.onStart { onStart() }.onCompletion { onCompletion() }.flowOn(Dispatchers.IO)

    fun insertMoviesListToDb(movies: List<Movie>): List<Long> {
        return movieDao.insertMoviesList(movies)
    }

    fun deleteAllMoviesFromDb() {
        return movieDao.deleteAllMovies()
    }
}
