package com.hardbobby.domain.usecase

import com.hardbobby.domain.common.Result
import com.hardbobby.domain.common.SimpleResult
import com.hardbobby.domain.common.usecase.BaseParamUseCase
import com.hardbobby.domain.dto.modelview.MovieCastModelView
import com.hardbobby.domain.dto.modelview.MovieDetailModelView
import com.hardbobby.domain.mapper.ImageMovieUrlMapper
import com.hardbobby.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created by Bobby.Hardian on 22/09/2021.
 */
class DetailMovieUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val mapper: ImageMovieUrlMapper
) : BaseParamUseCase<Int, MovieDetailModelView>() {
    override suspend fun execute(param: Int): Flow<SimpleResult<MovieDetailModelView>> {
        return movieRepository.getDetailMovies(param).map {
            it.mapData { response ->
                Result.Success.Data(
                    MovieDetailModelView(
                        response.originalMovieTitle,
                        response.overview,
                        mapper.mapFromSource(response.posterPath),
                        mapper.mapFromSource(response.backDropPath),
                        response.originalLanguage,
                        response.releaseDate,
                        response.voteAverage,
                        response.voteCount,
                        response.credits.cast.map { cast ->
                            MovieCastModelView(
                                cast.originalName,
                                mapper.mapFromSource(cast.profileUrl.orEmpty())
                            )
                        }
                    )
                )
            }
        }
    }
}