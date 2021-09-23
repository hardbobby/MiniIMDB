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
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.supervisorScope
import javax.inject.Inject

/**
 * Created by Bobby.Hardian on 21/09/2021.
 */
class PopularMovieUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val imageMovieUrlMapper: ImageMovieUrlMapper
) : BaseParamUseCase<Int, List<MovieItemModelView>>() {

    override suspend fun execute(param: Int): Flow<SimpleResult<List<MovieItemModelView>>> {
        return flow {
            try {
                val result = supervisorScope {
                    var isGenreListSuccess = false
                    var isFavoriteMovieSuccess = false
                    var isPopularMovieSuccess = false
                    val genreMovieList: MutableList<GenreResponse> = mutableListOf()
                    val localFavouriteMovieList: MutableList<MovieResponse> = mutableListOf()
                    val popularMovieList: MutableList<MovieResponse> = mutableListOf()

                    movieRepository.getFavoriteMoviesList().first().handleResult(
                        successDataBlock = { result ->
                            localFavouriteMovieList.addAll(result)
                            isFavoriteMovieSuccess = true
                        },
                        successNoDataBlock = {
                            localFavouriteMovieList.clear()
                            isFavoriteMovieSuccess = true
                        }
                    )

                    val popularSync = async {
                        movieRepository.getPopularMoviesList(param)
                    }
                    popularSync.await().collect {
                        it.handleResult(
                            successDataBlock = { result ->
                                popularMovieList.addAll(result)
                                isPopularMovieSuccess = true
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

                    if (isFavoriteMovieSuccess && isGenreListSuccess && isPopularMovieSuccess)
                        Result.Success.Data(
                            popularMovieList.map { response ->
                                MovieItemModelView(
                                    response.id,
                                    response.originalMovieTitle,
                                    response.overview,
                                    imageMovieUrlMapper.mapFromSource(response.posterUrl),
                                    localFavouriteMovieList.any { it.id == response.id },
                                    response.genreIdList.map { string ->
                                        genreMovieList.find { it.id == string }?.name.orEmpty()
                                    }
                                )
                            }
                        )
                    else if (isPopularMovieSuccess && localFavouriteMovieList.isNullOrEmpty()) {
                        Result.Success.Data(
                            popularMovieList.map { response ->
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
                    } else {
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