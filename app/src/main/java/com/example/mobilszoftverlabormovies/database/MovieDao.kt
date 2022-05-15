package com.example.mobilszoftverlabormovies.database

import androidx.room.*
import com.example.mobilszoftverlabormovies.model.Movie
import retrofit2.http.DELETE

@Dao
interface MovieDao {
    // leírja az adatbázis kéréseket

    // GET
    // Premierek
    // Népszerűek
    // Most játszottak

    @Query("SELECT * FROM Movie")
    fun getAllMovies(): List<Movie>

    @Query("SELECT * FROM Movie")
    fun getLatestMovies(): List<Movie>

    @Query("SELECT * FROM Movie")
    fun getPopularMovies(): List<Movie>

    @Query("SELECT * FROM Movie")
    fun getNowPlayingMovies(): List<Movie>

    @Query("SELECT * FROM Movie WHERE id = :movie_id")
    fun getMovie(movie_id: String): Movie

    // CREATE
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: Movie): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMoviesList(movies: List<Movie>): List<Long>

    // POST
    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateMovie(movie: Movie)

    // DELETE
    @Query("DELETE FROM Movie WHERE id = :movie_id")
    fun deleteMovie(movie_id: String)

    @Query("DELETE FROM Movie")
    fun deleteAllMovies()
}