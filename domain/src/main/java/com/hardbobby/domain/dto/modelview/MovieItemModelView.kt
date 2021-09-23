package com.hardbobby.domain.dto.modelview

/**
 * Created by Bobby.Hardian on 21/09/2021.
 */
data class MovieItemModelView(
    val idMovie: Int,
    val nameMovie: String,
    val overviewMovie: String,
    val posterUrl: String,
    var isFavourite: Boolean,
    val genreIdList: List<String>
)
