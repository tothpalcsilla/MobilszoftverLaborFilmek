package com.example.mobilszoftverlabormovies.database

import androidx.room.*
import com.example.mobilszoftverlabormovies.model.Movie
import retrofit2.http.DELETE

@Dao
interface MovieDao {
    // leírja az adatbázis kéréseket

    // GET
    @Query("SELECT * FROM Movie")
    fun getAllMovies(): List<Movie>
    // Top rated
    @Query("SELECT * FROM Movie ORDER BY vote_average")
    fun getLatestMovies(): List<Movie>
    // Népszerűek
    @Query("SELECT * FROM Movie ORDER BY popularity")
    fun getPopularMovies(): List<Movie>
    // Most játszottak
    @Query("SELECT * FROM Movie ORDER BY release_date")
    fun getNowPlayingMovies(): List<Movie>

    @Query("SELECT * FROM Movie WHERE id = :movie_id")
    fun getMovie(movie_id: String): Movie

    // CREATE
    @Insert
    fun insertMovie(movie: Movie): Long

    @Insert
    fun insertMoviesList(movies: List<Movie>): List<Long>

    // POST
    @Update
    fun updateMovie(movie: Movie)

    // DELETE
    @Delete
    fun deleteMovie(movie: Movie)

    @Query("DELETE FROM Movie")
    fun deleteAllMovies()
}