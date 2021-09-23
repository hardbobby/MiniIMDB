package com.hardbobby.domain.mapper

import com.hardbobby.domain.dto.modelview.MovieItemModelView
import com.hardbobby.domain.dto.response.MovieResponse

/**
 * Created by Bobby.Hardian on 22/09/2021.
 */
class MovieItemMapper : Mapper<MovieItemModelView, MovieResponse> {

    override fun mapFromSource(source: MovieItemModelView): MovieResponse {
        return MovieResponse(
            source.idMovie,
            source.nameMovie,
            source.overviewMovie,
            source.posterUrl,
            source.genreIdList
        )
    }
}