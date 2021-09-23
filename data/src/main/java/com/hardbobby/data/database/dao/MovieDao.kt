package com.hardbobby.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hardbobby.data.database.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created by Bobby.Hardian on 21/09/2021.
 */
@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateMovie(movie: MovieEntity)

    @Query("SELECT * from Movie")
    fun getListFavoriteMovie(): Flow<List<MovieEntity>>

    @Query("DELETE FROM Movie where idMovie = :idMovie")
    suspend fun deleteFavoriteMovie(idMovie: Int)

    @Query("SELECT COUNT(idMovie) from Movie")
    fun getFavoriteCounter(): Flow<Int>
}