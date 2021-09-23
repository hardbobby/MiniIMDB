package com.hardbobby.domain.dto.modelview

/**
 * Created by Bobby.Hardian on 22/09/2021.
 */
class MovieDetailModelView(
    val nameMovie: String,
    val overviewMovie: String,
    val posterUrl: String,
    val backdropUrl: String,
    val originalLanguage: String,
    val releaseDate: String,
    val voteAverage: Float,
    val voteCount: Int,
    val listCast: List<MovieCastModelView>
)