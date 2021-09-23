package com.hardbobby.domain.dto.response

import com.google.gson.annotations.SerializedName

/**
 * Created by Bobby.Hardian on 21/09/2021.
 */
data class MovieResponse(
    val id: Int,
    @field:SerializedName("original_title")
    val originalMovieTitle: String,
    val overview: String,
    @field:SerializedName("poster_path")
    val posterUrl: String,
    @field:SerializedName("genre_ids")
    val genreIdList: List<String>
)
