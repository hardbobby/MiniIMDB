package com.hardbobby.domain.usecase

import com.hardbobby.domain.common.Result
import com.hardbobby.domain.common.SimpleError
import com.hardbobby.domain.common.SimpleResult
import com.hardbobby.domain.common.usecase.BaseParamUseCase
import com.hardbobby.domain.dto.modelview.MovieItemModelView
import com.hardbobby.domain.dto.response.GenreResponse
import com.hardbobby.domain.dto.response.MovieResponse
import com.hardbobby.domain.mapper.ImageMovieUrlMapper
import com.hardbobby.domain.repository.MovieRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.supervisorScope
import javax.inject.Inject

/**
 * Created by Bobby.Hardian on 21/09/2021.
 */
class TopRatedMovieUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val imageMovieUrlMapper: ImageMovieUrlMapper
) : BaseParamUseCase<Int, List<MovieItemModelView>>() {

    override suspend fun execute(param: Int): Flow<SimpleResult<List<MovieItemModelView>>> {
        return flow {
            try {
                val result = supervisorScope {
                    var isGenreListSuccess = false
                    var isTopRatedListSuccess = false
                    val genreMovieList: MutableList<GenreResponse> = mutableListOf()
                    val topRatedMovieList: MutableList<MovieResponse> = mutableListOf()

                    val popularSync = async {
                        movieRepository.getTopRatedMoviesList(param)
                    }
                    popularSync.await().collect {
                        it.handleResult(
                            successDataBlock = { result ->
                                topRatedMovieList.addAll(result)
                                isTopRatedListSuccess = true
                            }
                        )
                    }

                    val genreList = async {
                        movieRepository.getGenreListMovies()
                    }
                    genreList.await().collect {
                        it.handleResult(
                            successDataBlock = { result ->
                                genreMovieList.addAll(result.genres)
                                isGenreListSuccess = true
                            }
                        )
                    }

                    if (isGenreListSuccess && isTopRatedListSuccess)
                        Result.Success.Data(
                            topRatedMovieList.map { response ->
                                MovieItemModelView(
                                    response.id,
                                    response.originalMovieTitle,
                                    response.overview,
                                    imageMovieUrlMapper.mapFromSource(response.posterUrl),
                                    false,
                                    response.genreIdList.map { string ->
                                        genreMovieList.find { it.id == string }?.name.orEmpty()
                                    }
                                )
                            }
                        )
                    else {
                        Result.Failure(SimpleError("No Data Available"))
                    }
                }
                emit(result)
            } catch (e: Exception) {
                Result.Failure(SimpleError("Caught an assertion error"))
            }
        }
    }
}