package com.example.mobilszoftverlabormovies.ui.details

import androidx.lifecycle.ViewModel
import com.example.mobilszoftverlabormovies.ui.list.DetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val detailRepository: DetailRepository
) : ViewModel() {

}