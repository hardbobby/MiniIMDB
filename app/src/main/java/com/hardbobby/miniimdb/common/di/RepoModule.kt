package com.hardbobby.miniimdb.common.di

import com.hardbobby.data.repositoryimpl.MovieRepositoryImpl
import com.hardbobby.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Bobby.Hardian on 21/09/2021.
 */
@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Provides
    @Singleton
    fun providesMovieRepository(movieRepositoryImpl: MovieRepositoryImpl): MovieRepository {
        return movieRepositoryImpl
    }
}