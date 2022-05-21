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
        val movies: List<Movie> = movieDao.getAllMovies()
        if (movies.isEmpty()) {
            // request API network call asynchronously.
            val response = movieApi.getMovies(
                api_key = Config.API_KEY,
                language = Config.LANGUAGE,
                include_adult = false,
                page = 1,
                query = "2022")
            /*if(response.isSuccessful){
                val responseData = response.body()
                val movieListResult: List<Movie>? = responseData?.results
                if(movieListResult != null){
                    insertMoviesListToDb(movieListResult)
                    movieList = movieListResult
                    emit(movieList)
                } else {
                    movieList = Collections.emptyList()
                }
            }*/
            response.enqueue(object : Callback<MovieListApiResponseModel> {
                override fun onFailure(call: Call<MovieListApiResponseModel>, t: Throwable) {
                    movieList = Collections.emptyList()
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
            emit(movieList)
        } else {
            emit(movies)
        }
    }.onStart { onStart() }.onCompletion { onCompletion() }.flowOn(Dispatchers.IO)

    /*fun getAllMovies() {
        if (isOnline) {
            val movieCall: Call<MovieListApiResponseModel> = movieApi.getAllMovies(
                language = Config.LANGUAGE,
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
    }*/

    fun getLatestMovies(): List<Movie> {
        val movieList: List<Movie> = if (isOnline) {
            movieApi.getLatestMovies(language = Config.LANGUAGE, page = 1)
        } else {
            movieDao.getLatestMovies()
        }
        insertMoviesListToDb(movieList)
        return movieList
    }

    fun getPopularMovies(): List<Movie> {
        val movieList: List<Movie> = if (isOnline) {
            movieApi.getPopularMovies(language = Config.LANGUAGE, page = 1)
        } else {
            movieDao.getPopularMovies()
        }
        insertMoviesListToDb(movieList)
        return movieList
    }

    fun getNowPlayingMovies(): List<Movie> {
        val movieList: List<Movie> = if (isOnline) {
            movieApi.getNowPlayingMovies(language = Config.LANGUAGE, page = 1)
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
