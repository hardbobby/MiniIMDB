package com.hardbobby.domain.dto.response

import com.google.gson.annotations.SerializedName

/**
 * Created by Bobby.Hardian on 22/09/2021.
 */
data class MovieDetailResponse(
    @field:SerializedName("backdrop_path")
    val backDropPath: String,
    @field:SerializedName("poster_path")
    val posterPath: String,
    @field:SerializedName("original_title")
    val originalMovieTitle: String,
    @field:SerializedName("original_language")
    val originalLanguage: String,
    val overview: String,
    @field:SerializedName("release_date")
    val releaseDate: String,
    @field:SerializedName("vote_average")
    val voteAverage: Float,
    @field:SerializedName("vote_count")
    val voteCount: Int,
    val credits: MovieCreditResponse,
)