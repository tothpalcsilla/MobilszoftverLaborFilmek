package com.example.mobilszoftverlabormovies.ui.details

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.mobilszoftverlabormovies.di.MoviesRepository
import com.example.mobilszoftverlabormovies.model.Movie

class DetailsViewModel(application: Application, movieId: String) : AndroidViewModel(application) {

    private val repository : MoviesRepository = MoviesRepository()
    private val movie: Movie = repository.getMovie(movieId)

    fun insert() {
        repository.insertMovie(movie)
    }

    fun delete() {
        repository.deleteMovie(movie)
    }
}