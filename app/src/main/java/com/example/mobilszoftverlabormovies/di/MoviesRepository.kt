package com.example.mobilszoftverlabormovies.di

import com.example.mobilszoftverlabormovies.Config
import com.example.mobilszoftverlabormovies.database.MovieDao
import com.example.mobilszoftverlabormovies.model.Movie
import com.example.mobilszoftverlabormovies.model.network.MovieListApiResponseModel
import com.example.mobilszoftverlabormovies.network.MoviesApi
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Module
@InstallIn(SingletonComponent::class)
class MoviesRepository {

    private var isOnline: Boolean = Config.isOnline
    private val movieDao: MovieDao = DatabaseModule
    private val movieApi: MoviesApi = ApiModule

    /*private val movieApi: MoviesApi =
        ApiModule.provideMoviesApi(ApiModule.provideRetrofit(ApiModule.provideOkHttpClient()))*/

    var movieList: List<Movie> = emptyList()

    fun getAllMovies() {
        if (isOnline) {
            /*val movieCall: Call<MovieListApiResponseModel> = movieApi.getAllMovies(
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
                        movieList = emptyList()
                    }
                }
            })*/
        } else {
            movieList = movieDao.getAllMovies()
        }
    }

    /*fun getLatestMovies(): List<Movie> {
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
    }*/
}