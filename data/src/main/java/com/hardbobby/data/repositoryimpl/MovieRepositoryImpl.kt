package com.hardbobby.data.repositoryimpl

import com.hardbobby.data.database.dao.MovieDao
import com.hardbobby.data.database.entity.MovieEntity
import com.hardbobby.data.remote.MovieAPI
import com.hardbobby.domain.common.Result
import com.hardbobby.domain.common.SimpleResult
import com.hardbobby.domain.common.mapToResponseResult
import com.hardbobby.domain.common.mapToResult
import com.hardbobby.domain.dto.response.BaseGenreResponse
import com.hardbobby.domain.dto.response.MovieDetailResponse
import com.hardbobby.domain.dto.response.MovieResponse
import com.hardbobby.domain.repository.MovieRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject

/**
 * Created by Bobby.Hardian on 21/09/2021.
 */
class MovieRepositoryImpl @Inject constructor(
    private val movieAPI: MovieAPI,
    private val movieDao: MovieDao
) : MovieRepository {

    override suspend fun getPopularMoviesList(pageNum: Int): Flow<SimpleResult<List<MovieResponse>>> {
        return movieAPI.getPopularMovieList(pageNum).mapToResult()
    }

    override suspend fun getTopRatedMoviesList(pageNum: Int): Flow<SimpleResult<List<MovieResponse>>> {
        return movieAPI.getTopRatedMovieList(pageNum).mapToResult()
    }

    override suspend fun getGenreListMovies(): Flow<SimpleResult<BaseGenreResponse>> {
        return movieAPI.getGenreMovieList().mapToResponseResult()
    }

    override suspend fun getFavoriteMoviesList(): Flow<SimpleResult<List<MovieResponse>>> {
        return movieDao.getListFavoriteMovie().map {
            if (!it.isNullOrEmpty()) {
                Result.Success.Data(it.map { item ->
                    MovieResponse(
                        item.idMovie,
                        item.titleMovie,
                        "",
                        item.posterUrl,
                        item.genreList
                    )
                })
            } else {
                Result.Success.NoData
            }
        }
    }

    override suspend fun getDetailMovies(id: Int): Flow<SimpleResult<MovieDetailResponse>> {
        return movieAPI.getMovieDetail(id).mapToResponseResult()
    }

    override suspend fun saveFavoriteMovie(movieResponse: MovieResponse): Flow<SimpleResult<Boolean>> {
        val result = try {
            movieDao.insertOrUpdateMovie(
                MovieEntity(
                    movieResponse.id,
                    movieResponse.originalMovieTitle,
                    movieResponse.genreIdList,
                    movieResponse.posterUrl
                )
            )
            Result.Success.Data(true)
        } catch (e: Exception) {
            Result.Success.Data(false)
        }
        return flowOf(result)
    }

    override suspend fun deleteFavoriteMovie(movieId: Int): Flow<SimpleResult<Boolean>> {
        val result = try {
            movieDao.deleteFavoriteMovie(movieId)
            Result.Success.Data(true)
        } catch (e: Exception) {
            Result.Success.Data(false)
        }
        return flowOf(result)
    }

    override suspend fun getTotalFavoriteMovie(): Flow<SimpleResult<Int>> {
        return flow {
            try {
                movieDao.getFavoriteCounter().collect { resultSize ->
                    if (resultSize == 0) {
                        emit(Result.Success.NoData)
                    } else {
                        emit(Result.Success.Data(resultSize))
                    }
                }
            } catch (e: Exception) {
                emit(Result.Success.NoData)
            }
        }
    }
}