package com.example.mobilszoftverlabormovies.model.network

import com.example.mobilszoftverlabormovies.model.Movie
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieListApiResponseModel
    (
    val page: Int,
    val results: List<Movie>,
    val total_results: Int,
    val total_pages: Int
)

@JsonClass(generateAdapter = true)
data class MovieApiResponseModel(
    val poster_path: String?,
    val adult: Boolean,
    val overview: String,
    val release_date: String,

    val title: String,

    val popularity: Double,

    val vote_average: Double
)