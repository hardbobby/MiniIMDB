package com.hardbobby.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hardbobby.data.database.converter.Converters
import com.hardbobby.data.database.dao.MovieDao
import com.hardbobby.data.database.entity.MovieEntity

/**
 * Created by Bobby.Hardian on 21/09/2021.
 */
@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class MovieIMDBDatabase : RoomDatabase() {
    abstract fun getMovieDao(): MovieDao
}