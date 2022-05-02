package com.example.mobilszoftverlabormovies.network

import com.example.mobilszoftverlabormovies.model.Movie

interface MoviesApi {
    // leírja az api hívásokat

    // Filmek listája
    // GET
    fun getAllMovies(): List<Movie>

    // Film részletei
    // GET
    // /movie/{movie_id}
    fun getMovie(movie_id: String): Movie

    // Premierek
    // GET
    // /movie/latest
    fun getLatestMovies(): List<Movie>

    // Népszerűek
    // GET
    // /movie/popular
    fun getPopularMovies(): List<Movie>

    // Most játszottak
    // GET
    // /movie/now_playing
    fun getNowPlayingMovies(): List<Movie>

    // CREATE
    fun insertMovie(movie: Movie): Long
    fun insertMoviesList(movies: List<Movie>): List<Long>

    // POST
    fun updateMovie(movie: Movie)

    // DELETE
    fun deleteMovie(movie: Movie)
    fun deleteAllMovies()
}