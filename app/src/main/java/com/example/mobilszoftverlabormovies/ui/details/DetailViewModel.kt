package com.example.mobilszoftverlabormovies.ui.details

import androidx.lifecycle.ViewModel
import com.example.mobilszoftverlabormovies.ui.list.DetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val detailRepository: DetailRepository
) : ViewModel() {
    private val movieIdSharedFlow: MutableSharedFlow<Long> = MutableSharedFlow(replay = 1)

    val movieDetailsFlow = movieIdSharedFlow.flatMapLatest {
        detailRepository.getMovie(it)
    }

    fun loadMovieById(id: Long) = movieIdSharedFlow.tryEmit(id)
}