package com.example.mobilszoftverlabormovies.ui.list

import androidx.lifecycle.ViewModel
import com.example.mobilszoftverlabormovies.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    listRepository: ListRepository
) : ViewModel() {

    var movieList: List<Movie> = listRepository.movieList

    init {
        listRepository.getAllMovies()
        movieList = listRepository.movieList
    }
}