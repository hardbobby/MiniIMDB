package com.hardbobby.data.di

import android.content.Context
import androidx.room.Room
import com.hardbobby.data.common.Constants
import com.hardbobby.data.database.MovieIMDBDatabase
import com.hardbobby.data.database.converter.Converters
import com.hardbobby.data.database.dao.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Bobby.Hardian on 21/09/2021.
 */
@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): MovieIMDBDatabase {
        return Room.databaseBuilder(
            appContext, MovieIMDBDatabase::class.java,
            Constants.DatabaseName
        )
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieDao(movieIMDBDatabase: MovieIMDBDatabase): MovieDao {
        return movieIMDBDatabase.getMovieDao()
    }
}