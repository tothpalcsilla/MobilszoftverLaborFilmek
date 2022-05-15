package com.example.mobilszoftverlabormovies.di

import com.example.mobilszoftverlabormovies.Config
import com.example.mobilszoftverlabormovies.network.MoviesApi
import com.example.mobilszoftverlabormovies.model.Movie
import com.example.mobilszoftverlabormovies.model.network.MovieListApiResponseModel
import com.example.mobilszoftverlabormovies.network.ApiKeyInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Query
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule : MoviesApi{
    /*override fun getAllMovies(
        @Query(value = "api_key") api_key: String,
        @Query(value = "query") query: String,
        @Query(value = "language") language: String,
        @Query(value = "page") page: Int,
        @Query(value = "include_adult") include_adult: Boolean
    ): Call<MovieListApiResponseModel> {
        TODO("Not yet implemented")

    }

    override fun getLatestMovies(): List<Movie> {
        TODO("Not yet implemented")
    }

    override fun getPopularMovies(): List<Movie> {
        TODO("Not yet implemented")
    }

    override fun getNowPlayingMovies(): List<Movie> {
        TODO("Not yet implemented")
    }

    override fun getMovie(movie_id: String): Movie {
        TODO("Not yet implemented")
    }

    override fun insertMovie(movie: Movie): Long {
        TODO("Not yet implemented")
    }

    override fun insertMoviesList(movies: List<Movie>): List<Long> {
        TODO("Not yet implemented")
    }

    override fun updateMovie(movie: Movie) {
        TODO("Not yet implemented")
    }

    override fun deleteMovie(movie: Movie) {
        TODO("Not yet implemented")
    }

    override fun deleteAllMovies() {
        TODO("Not yet implemented")
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(ApiKeyInterceptor())
            .connectTimeout(15, TimeUnit.SECONDS) // connect timeout
            .readTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(Config.base_url)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideMoviesApi(retrofit: Retrofit): MoviesApi {
        return retrofit.create(MoviesApi::class.java)
    }*/
}