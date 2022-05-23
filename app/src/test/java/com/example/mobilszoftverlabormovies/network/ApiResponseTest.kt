package com.example.mobilszoftverlabormovies.network

import com.example.mobilszoftverlabormovies.Config
import com.example.mobilszoftverlabormovies.database.MovieDao
import com.example.mobilszoftverlabormovies.model.Movie
import com.example.mobilszoftverlabormovies.model.network.MovieListApiResponseModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import junit.framework.Assert.assertEquals
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import java.io.BufferedReader
import java.io.InputStreamReader

@RunWith(JUnit4::class)
class ApiResponseTest : ApiAbstract<MoviesApi>() {

    @Test
    fun testGetAllMovies(){
        enqueueResponse("Movies.json")
        val movieApi = createService(MoviesApi::class.java)
        val response = movieApi.getAllMovies(api_key = Config.API_KEY,
            language = Config.LANGUAGE,
            include_adult = false,
            page = 1,
            query = "lion").execute()
        val responseData = response.body()
        val movieListResult: List<Movie>? = responseData?.results
        assertEquals(movieListResult?.size, 20)
        assertEquals(responseData?.total_results, 622)
    }

    @Test
    fun testGetTopRatedMovies(){
        enqueueResponse("TopRatedMovies.json")
        val movieApi = createService(MoviesApi::class.java)
        val response = movieApi.getTopRatedMovies(api_key = Config.API_KEY,
            language = Config.LANGUAGE,
            page = 1).execute()
        val responseData = response.body()
        val movieListResult: List<Movie>? = responseData?.results
        assertEquals(movieListResult?.size, 20)
        assertEquals(responseData?.total_pages, 499)
        assertEquals(responseData?.total_results, 9963)
    }

    @Test
    fun testGetPopularMovies(){
        enqueueResponse("PopularMovies.json")
        val movieApi = createService(MoviesApi::class.java)
        val response = movieApi.getPopularMovies(api_key = Config.API_KEY,
            language = Config.LANGUAGE,
            page = 1).execute()
        val responseData = response.body()
        val movieListResult: List<Movie>? = responseData?.results
        assertEquals(movieListResult?.size, 20)
        assertEquals(movieListResult?.first()?.title, "Az elveszett város")
        assertEquals(responseData?.total_pages, 33631)
        assertEquals(responseData?.total_results, 672609)
    }

    @Test
    fun testGetNowPlayingMovies(){
        enqueueResponse("LatestMovies.json")
        val movieApi = createService(MoviesApi::class.java)
        val response = movieApi.getNowPlayingMovies(api_key = Config.API_KEY,
            language = Config.LANGUAGE,
            page = 1).execute()
        val responseData = response.body()
        val movieListResult: List<Movie>? = responseData?.results
        assertEquals(movieListResult?.size, 20)
        assertEquals(responseData?.total_results, 1268)

        /*val gson: Gson = GsonBuilder().create()
        val result: MovieListApiResponseModel = gson.fromJson(
            readJsonFile("Movies.json"),
            MovieListApiResponseModel::class.java
        )

        assertEquals(responseData, result);*/
    }

}
