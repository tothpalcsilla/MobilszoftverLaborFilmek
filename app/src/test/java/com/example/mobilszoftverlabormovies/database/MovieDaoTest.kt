package com.example.mobilszoftverlabormovies.database

import com.example.mobilszoftverlabormovies.model.Movie
import com.example.mobilszoftverlabormovies.model.network.MovieListApiResponseModel
import com.example.mobilszoftverlabormovies.utils.MockTestUtil.mockMovieList
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import junit.framework.Assert
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.io.BufferedReader
import java.io.InputStreamReader

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [21])
class MovieDaoTest  : LocalDatabase() {

    private lateinit var movieDao: MovieDao

    @Before
    fun init() {
        movieDao = db.movieDao()
    }

    @Test
    fun testInsertOneMovieAndLoadMovieList() = runBlocking {
        val mockDataList = mockMovieList()
        movieDao.insertMoviesList(mockDataList)

        val loadFromDB = movieDao.getAllMovies()
        assertThat(loadFromDB.toString(), `is`(mockDataList.toString()))

        val mockData = Movie.mock()
        assertThat(loadFromDB[0].toString(), `is`(mockData.toString()))
    }

    @Test
    fun testInsertMovieListAndLoadAllMovies() = runBlocking {

        val gson: Gson = GsonBuilder().create()
        val response: MovieListApiResponseModel = gson.fromJson(
            readJsonFile("Movies.json"),
            MovieListApiResponseModel::class.java
        )
        val mockDataList: List<Movie> = response.results

        movieDao.insertMoviesList(mockDataList)

        val loadFromDB = movieDao.getAllMovies()

        assertEquals(loadFromDB.size, mockDataList.size)
        assertTrue(loadFromDB.contains(mockDataList.first()))
        assertTrue(loadFromDB.contains(mockDataList.last()))
    }

    @Test
    fun testInsertMovieListAndLoadTopRatedMovies() = runBlocking {

        val gson: Gson = GsonBuilder().create()
        val response: MovieListApiResponseModel = gson.fromJson(
            readJsonFile("TopRatedMovies.json"),
            MovieListApiResponseModel::class.java
        )
        val mockDataList: List<Movie> = response.results

        movieDao.insertMoviesList(mockDataList)

        val loadFromDB = movieDao.getTopRatedMovies()

        assertEquals(loadFromDB.size, mockDataList.size)
        assertTrue(loadFromDB.contains(mockDataList.first()))
        assertTrue(loadFromDB.contains(mockDataList.last()))
        assertTrue(loadFromDB[0].vote_average >= loadFromDB[1].vote_average)
        assertTrue(loadFromDB.first().vote_average >= loadFromDB.last().vote_average)
    }

    @Test
    fun testInsertMovieListAndLoadPopularMovies() = runBlocking {

        val gson: Gson = GsonBuilder().create()
        val response: MovieListApiResponseModel = gson.fromJson(
            readJsonFile("PopularMovies.json"),
            MovieListApiResponseModel::class.java
        )
        val mockDataList: List<Movie> = response.results

        movieDao.insertMoviesList(mockDataList)

        val loadFromDB = movieDao.getPopularMovies()

        assertEquals(loadFromDB.size, mockDataList.size)
        assertTrue(loadFromDB.contains(mockDataList.first()))
        assertTrue(loadFromDB.contains(mockDataList.last()))
        assertTrue(loadFromDB[0].popularity >= loadFromDB[1].popularity)
        assertTrue(loadFromDB.first().popularity >= loadFromDB.last().popularity)
    }

    @Test
    fun testInsertMovieListAndLoadLatestMovies() = runBlocking {

        val gson: Gson = GsonBuilder().create()
        val response: MovieListApiResponseModel = gson.fromJson(
            readJsonFile("LatestMovies.json"),
            MovieListApiResponseModel::class.java
        )
        val mockDataList: List<Movie> = response.results

        movieDao.insertMoviesList(mockDataList)

        val loadFromDB = movieDao.getNowPlayingMovies()

        assertEquals(loadFromDB.size, mockDataList.size)
        assertTrue(loadFromDB.contains(mockDataList.first()))
        assertTrue(loadFromDB.contains(mockDataList.last()))
        assertTrue(loadFromDB[0].release_date >= loadFromDB[1].release_date)
        assertTrue(loadFromDB.first().release_date >= loadFromDB.last().release_date)
    }

    fun readJsonFile(filename: String): String {
        val br = BufferedReader(InputStreamReader(this.javaClass.classLoader.getResourceAsStream(filename)))
        val sb: StringBuilder = StringBuilder()
        var line: String? = br.readLine()
        while (line != null) {
            sb.append(line)
            line = br.readLine()
        }
        return sb.toString()
    }
}
