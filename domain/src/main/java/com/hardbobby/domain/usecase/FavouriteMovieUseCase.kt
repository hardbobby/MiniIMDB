package com.hardbobby.domain.usecase

import com.hardbobby.domain.common.Result
import com.hardbobby.domain.common.SimpleResult
import com.hardbobby.domain.common.usecase.BaseUseCase
import com.hardbobby.domain.dto.modelview.MovieItemModelView
import com.hardbobby.domain.mapper.ImageMovieUrlMapper
import com.hardbobby.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created by Bobby.Hardian on 21/09/2021.
 */
class FavouriteMovieUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val mapper: ImageMovieUrlMapper
) : BaseUseCase<List<MovieItemModelView>>() {

    override suspend fun execute(): Flow<SimpleResult<List<MovieItemModelView>>> {
        return movieRepository.getFavoriteMoviesList().map { data ->
            data.mapData(map = { response ->
                Result.Success.Data(
                    response.map {
                        MovieItemModelView(
                            it.id,
                            it.originalMovieTitle,
                            it.overview,
                            mapper.mapFromSource(it.posterUrl),
                            true,
                            it.genreIdList
                        )
                    }
                )
            })
        }
    }
}