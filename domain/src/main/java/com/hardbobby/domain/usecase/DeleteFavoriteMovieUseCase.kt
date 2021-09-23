package com.hardbobby.domain.usecase

import com.hardbobby.domain.common.SimpleResult
import com.hardbobby.domain.common.usecase.BaseParamUseCase
import com.hardbobby.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Bobby.Hardian on 22/09/2021.
 */
class DeleteFavoriteMovieUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) : BaseParamUseCase<Int, Boolean>() {
    override suspend fun execute(param: Int): Flow<SimpleResult<Boolean>> {
        return movieRepository.deleteFavoriteMovie(param)
    }
}