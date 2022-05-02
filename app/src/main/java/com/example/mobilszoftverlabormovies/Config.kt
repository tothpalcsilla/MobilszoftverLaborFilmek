package com.example.mobilszoftverlabormovies

class Config {
    companion object {
        const val base_url = "https://image.tmdb.org/t/p/w500"
        const val API_URL = "https://api.themoviedb.org/3/search/movie"
        const val API_KEY = "78ac7c5c1a2abbf4562073ecc12de668"
        const val limit = 10

        var isOnline: Boolean = false
    }
}