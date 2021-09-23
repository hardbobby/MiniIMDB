package com.hardbobby.miniimdb.common.di

import com.hardbobby.data.repositoryimpl.MovieRepositoryImpl
import com.hardbobby.domain.mapper.ImageMovieUrlMapper
import com.hardbobby.domain.mapper.MovieItemMapper
import com.hardbobby.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Singleton

/**
 * Created by Bobby.Hardian on 21/09/2021.
 */
@Module
@InstallIn(ActivityComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun providesFavoriteUseCase(
        repository: MovieRepositoryImpl,
        mapper: ImageMovieUrlMapper
    ): FavouriteMovieUseCase {
        return FavouriteMovieUseCase(repository, mapper)
    }

    @Provides
    @Singleton
    fun providesPopularMovieUseCase(
        repository: MovieRepositoryImpl,
        mapper: ImageMovieUrlMapper
    ): PopularMovieUseCase {
        return PopularMovieUseCase(repository, mapper)
    }

    @Provides
    @Singleton
    fun providesTopRatedMovieUseCase(
        repository: MovieRepositoryImpl,
        mapper: ImageMovieUrlMapper
    ): TopRatedMovieUseCase {
        return TopRatedMovieUseCase(repository, mapper)
    }

    @Provides
    @Singleton
    fun providesSaveFavoriteUseCase(
        repository: MovieRepositoryImpl,
        mapper: MovieItemMapper
    ): SaveFavoriteMovieUseCase {
        return SaveFavoriteMovieUseCase(repository, mapper)
    }

    @Provides
    @Singleton
    fun providesDeleteFavoriteMovieUseCase(
        repository: MovieRepositoryImpl
    ): DeleteFavoriteMovieUseCase {
        return DeleteFavoriteMovieUseCase(repository)
    }

    @Provides
    @Singleton
    fun providesFavoriteMovieCounterUseCase(
        repository: MovieRepositoryImpl
    ): FavoriteMovieCounterUseCase {
        return FavoriteMovieCounterUseCase(repository)
    }

    @Provides
    @Singleton
    fun providesDetailMovieUseCase(
        repository: MovieRepositoryImpl,
        mapper: ImageMovieUrlMapper
    ): DetailMovieUseCase {
        return DetailMovieUseCase(repository, mapper)
    }
}