package com.example.mobilszoftverlabormovies.ui.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.mobilszoftverlabormovies.di.MoviesRepository
import com.example.mobilszoftverlabormovies.model.Movie

class ListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository : MoviesRepository = MoviesRepository()
    private val movieList: List<Movie> = repository.movieList

    fun getLatestMovies() {
        repository.getLatestMovies()
    }
}