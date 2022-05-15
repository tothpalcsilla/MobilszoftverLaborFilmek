package com.example.mobilszoftverlabormovies.di

import com.example.mobilszoftverlabormovies.database.MovieDao
import com.example.mobilszoftverlabormovies.network.MoviesApi
import com.example.mobilszoftverlabormovies.ui.list.ListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideMainRepository(
        movieApi: MoviesApi,
        //moviesDao: MovieDao
    ): ListRepository {
        return ListRepository(
            movieApi
            //,moviesDao
        )
    }
}