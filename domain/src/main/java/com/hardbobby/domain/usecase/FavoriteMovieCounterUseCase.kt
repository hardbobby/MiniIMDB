package com.hardbobby.domain.usecase

import com.hardbobby.domain.common.SimpleResult
import com.hardbobby.domain.common.usecase.BaseUseCase
import com.hardbobby.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Bobby.Hardian on 23/09/2021.
 */
class FavoriteMovieCounterUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) : BaseUseCase<Int>() {
    override suspend fun execute(): Flow<SimpleResult<Int>> {
        return movieRepository.getTotalFavoriteMovie()
    }
}