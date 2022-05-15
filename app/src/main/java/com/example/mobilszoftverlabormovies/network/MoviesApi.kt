package com.example.mobilszoftverlabormovies.network

import com.example.mobilszoftverlabormovies.model.Movie
import com.example.mobilszoftverlabormovies.model.network.MovieListApiResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {
    // leírja az api hívásokat

    // Filmek listája
    /*@GET("/search/movie")
    fun getAllMovies(@Query("api_key") api_key: String,
                     @Query("query") query: String,
                     @Query("language") language: String,
                     @Query("page") page: Int,
                     @Query("include_adult") include_adult: Boolean
    ): Call<MovieListApiResponseModel>

    // Film részletei
    @GET("/movie/{movie_id}")
    fun getMovie(movie_id: String): Movie

    // Premierek
    @GET("/movie/now_playing")
    fun getLatestMovies(): List<Movie>

    // Népszerűek
    @GET("movie/popular")
    fun getPopularMovies(): List<Movie>

    // Most játszottak
    @GET("/movie/now_playing")
    fun getNowPlayingMovies(): List<Movie>

    // POST
    fun insertMovie(movie: Movie): Long
    fun insertMoviesList(movies: List<Movie>): List<Long>

    // PUT
    fun updateMovie(movie: Movie)

    // DELETE
    fun deleteMovie(movie: Movie)
    fun deleteAllMovies()*/
}