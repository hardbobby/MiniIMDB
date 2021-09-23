package com.hardbobby.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Bobby.Hardian on 21/09/2021
 */
@Entity(tableName = "Movie")
data class MovieEntity (
    @PrimaryKey
    val idMovie :Int,
    val titleMovie:String,
    val genreList :List<String>,
    val posterUrl:String,
)