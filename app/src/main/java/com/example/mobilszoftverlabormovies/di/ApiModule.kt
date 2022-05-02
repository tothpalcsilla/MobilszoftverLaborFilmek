package com.example.mobilszoftverlabormovies.di

import com.example.mobilszoftverlabormovies.network.MoviesApi
import com.example.mobilszoftverlabormovies.model.Movie
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ApiModule : MoviesApi{
    override fun getAllMovies(): List<Movie> {
        TODO("Not yet implemented")
    }

    override fun getLatestMovies(): List<Movie> {
        TODO("Not yet implemented")
    }

    override fun getPopularMovies(): List<Movie> {
        TODO("Not yet implemented")
    }

    override fun getNowPlayingMovies(): List<Movie> {
        TODO("Not yet implemented")
    }

    override fun getMovie(movie_id: String): Movie {
        TODO("Not yet implemented")
    }

    override fun insertMovie(movie: Movie): Long {
        TODO("Not yet implemented")
    }

    override fun insertMoviesList(movies: List<Movie>): List<Long> {
        TODO("Not yet implemented")
    }

    override fun updateMovie(movie: Movie) {
        TODO("Not yet implemented")
    }

    override fun deleteMovie(movie: Movie) {
        TODO("Not yet implemented")
    }

    override fun deleteAllMovies() {
        TODO("Not yet implemented")
    }
}