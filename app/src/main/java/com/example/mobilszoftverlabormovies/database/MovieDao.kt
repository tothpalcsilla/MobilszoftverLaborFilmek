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
    @Query("SELECT * FROM Movie ORDER BY vote_average DESC")
    fun getTopRatedMovies(): List<Movie>
    // Népszerűek
    @Query("SELECT * FROM Movie ORDER BY popularity DESC")
    fun getPopularMovies(): List<Movie>
    // Most játszottak
    @Query("SELECT * FROM Movie ORDER BY release_date DESC")
    fun getNowPlayingMovies(): List<Movie>

    @Query("SELECT * FROM Movie WHERE id = :movie_id")
    fun getMovie(movie_id: Long): Movie

    // CREATE
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: Movie): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
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