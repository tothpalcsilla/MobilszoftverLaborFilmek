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
    @GET("/3/search/movie")
    fun getAllMovies(
        @Query("api_key") api_key: String,
        @Query("query") query: String,
        @Query("language") language: String,
        @Query("page") page: Int,
        @Query("include_adult") include_adult: Boolean
    ): Call<MovieListApiResponseModel>

    // Film részletei
    @GET("/3/movie/{movie_id}")
    fun getMovie(
        @Query("api_key") api_key: String,
        @Query("movie_id") movie_id: Long,
        //@Query("query") query: String,
        @Query("language") language: String,
        //@Query("append_to_response") append_to_response: String
    ): Call<Movie>

    // Top rated
    @GET("/3/movie/top_rated")
    fun getTopRatedMovies(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("page") page: Int,
        //@Query("region") region: String
    ): Call<MovieListApiResponseModel>

    // Népszerűek
    @GET("/3/movie/popular")
    fun getPopularMovies(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("page") page: Int,
        //@Query("region") region: String
    ): Call<MovieListApiResponseModel>

    // Most játszottak
    @GET("/3/movie/now_playing")
    fun getNowPlayingMovies(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("page") page: Int,
        //@Query("region") region: String
    ): Call<MovieListApiResponseModel>

    // POST
    fun insertMovie(movie: Movie): Long

    // PUT
    fun updateMovie(movie: Movie)

    // DELETE
    fun deleteMovie(movie: Movie)
}