package com.example.mobilszoftverlabormovies.network

import com.example.mobilszoftverlabormovies.model.Movie
import com.example.mobilszoftverlabormovies.model.network.MovieListApiResponseModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {
    // leírja az api hívásokat

    // Filmek listája
    @GET("/search/movie")
    fun getMovies(
        @Query("query") query: String,
        @Query("language") language: String,
        @Query("page") page: Int,
        @Query("include_adult") include_adult: Boolean
    ): Response<MovieListApiResponseModel>

    // Filmek listája
    @GET("/search/movie")
    fun getAllMovies(
        @Query("query") query: String,
        @Query("language") language: String,
        @Query("page") page: Int,
        @Query("include_adult") include_adult: Boolean
    ): Call<MovieListApiResponseModel>

    // Film részletei
    @GET("/movie/{movie_id}")
    fun getMovie(
        @Query("movie_id") movie_id: String,
        //@Query("query") query: String,
        @Query("language") language: String,
        //@Query("append_to_response") append_to_response: String
    ): Movie

    // Top rated
    @GET("/movie/top_rated")
    fun getLatestMovies(
        @Query("language") language: String,
        @Query("page") page: Int,
        //@Query("region") region: String
    ): List<Movie>

    // Népszerűek
    @GET("movie/popular")
    fun getPopularMovies(
        @Query("language") language: String,
        @Query("page") page: Int,
        //@Query("region") region: String
    ): List<Movie>

    // Most játszottak
    @GET("/movie/now_playing")
    fun getNowPlayingMovies(
        @Query("language") language: String,
        @Query("page") page: Int,
        //@Query("region") region: String
    ): List<Movie>

    // POST
    fun insertMovie(movie: Movie): Long

    // PUT
    fun updateMovie(movie: Movie)

    // DELETE
    fun deleteMovie(movie: Movie)
}