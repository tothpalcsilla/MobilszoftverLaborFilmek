package com.example.mobilszoftverlabormovies.ui.list

import androidx.annotation.StringRes
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilszoftverlabormovies.Config
import com.example.mobilszoftverlabormovies.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class
ListViewModel @Inject constructor(
    listRepository: ListRepository
) : ViewModel() {

    private val _isLoading: MutableState<Boolean> = mutableStateOf(false)
    val isLoading: State<Boolean> get() = _isLoading

    private val _selectedMenu: MutableState<Int> = mutableStateOf(Config.menuIndex)
    val selectedMenu: State<Int> get() = _selectedMenu

    fun selectMenu(@StringRes menu: Int) {
        _selectedMenu.value = menu
    }

    var movieList: Flow<List<Movie>> = listRepository.loadMovies(
        _selectedMenu.value,
        onStart = { _isLoading.value = true },
        onCompletion = { _isLoading.value = false }
    )
}