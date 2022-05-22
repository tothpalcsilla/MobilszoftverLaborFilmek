package com.example.mobilszoftverlabormovies.model.network

import androidx.compose.runtime.Immutable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mobilszoftverlabormovies.model.Movie

@Entity
data class MovieListApiResponseModel(
    val page: Int,
    val results: List<Movie>,
    val total_results: Int,
    val total_pages: Int
) {
    companion object {

        fun mock() = Movie(
            id = 0,
            title = "Captain America: The First Avenger",
            overview = "During World War II, Steve Rogers is a sickly man from Brooklyn who's transformed into super-soldier Captain America to aid in the war effort. Rogers must stop the Red Skull – Adolf Hitler's ruthless head of weaponry, and the leader of an organization that intends to use a mysterious device of untold powers for world domination.",
            poster_path = "/vSNxAJTlD0r02V9sPYpOjqDZXUK.jpg",
            popularity = 75.739,
            vote_average = 6.9,
            release_date = "2011-07-22"
        )

    }
}

/*
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
)*/