package com.example.mobilszoftverlabormovies.database

import com.example.mobilszoftverlabormovies.model.Movie

interface MovieDao {
    // leírja az adatbázis kéréseket

    // GET
    // Premierek
    // Népszerűek
    // Most játszottak
    fun getAllMovies(): List<Movie>
    fun getLatestMovies(): List<Movie>
    fun getPopularMovies(): List<Movie>
    fun getNowPlayingMovies(): List<Movie>

    fun getMovie(movie_id: String): Movie

    // CREATE
    fun insertMovie(movie: Movie): Long
    fun insertMoviesList(movies: List<Movie>): List<Long>

    // POST
    fun updateMovie(movie: Movie)

    // DELETE
    fun deleteMovie(movie: Movie)
    fun deleteAllMovies()
}