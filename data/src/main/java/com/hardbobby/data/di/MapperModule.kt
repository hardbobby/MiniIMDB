package com.hardbobby.data.di

import com.hardbobby.domain.mapper.ImageMovieUrlMapper
import com.hardbobby.domain.mapper.MovieItemMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Bobby.Hardian on 22/09/2021.
 */
@Module
@InstallIn(SingletonComponent::class)
object MapperModule {

    @Provides
    @Singleton
    fun providesImageMovieUrlMapper(): ImageMovieUrlMapper {
        return ImageMovieUrlMapper()
    }

    @Provides
    @Singleton
    fun providesIMovieItemMapper(): MovieItemMapper {
        return MovieItemMapper()
    }
}