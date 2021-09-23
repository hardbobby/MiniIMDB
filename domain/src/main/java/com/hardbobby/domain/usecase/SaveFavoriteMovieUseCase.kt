package com.hardbobby.domain.usecase

import com.hardbobby.domain.common.SimpleResult
import com.hardbobby.domain.common.usecase.BaseParamUseCase
import com.hardbobby.domain.dto.modelview.MovieItemModelView
import com.hardbobby.domain.mapper.MovieItemMapper
import com.hardbobby.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Bobby.Hardian on 22/09/2021.
 */
class SaveFavoriteMovieUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val mapper: MovieItemMapper
) : BaseParamUseCase<MovieItemModelView, Boolean>() {

    override suspend fun execute(param: MovieItemModelView): Flow<SimpleResult<Boolean>> {
        return movieRepository.saveFavoriteMovie(mapper.mapFromSource(param))
    }
}