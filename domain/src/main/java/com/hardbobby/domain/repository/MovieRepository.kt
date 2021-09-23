package com.hardbobby.domain.repository

import com.hardbobby.domain.common.SimpleResult
import com.hardbobby.domain.dto.response.BaseGenreResponse
import com.hardbobby.domain.dto.response.MovieDetailResponse
import com.hardbobby.domain.dto.response.MovieResponse
import kotlinx.coroutines.flow.Flow

/**
 * Created by Bobby.Hardian on 21/09/2021.
 */
interface MovieRepository {

    suspend fun getPopularMoviesList(pageNum: Int): Flow<SimpleResult<List<MovieResponse>>>

    suspend fun getTopRatedMoviesList(pageNum: Int): Flow<SimpleResult<List<MovieResponse>>>

    suspend fun getFavoriteMoviesList(): Flow<SimpleResult<List<MovieResponse>>>

    suspend fun getGenreListMovies(): Flow<SimpleResult<BaseGenreResponse>>

    suspend fun saveFavoriteMovie(movieResponse: MovieResponse): Flow<SimpleResult<Boolean>>

    suspend fun deleteFavoriteMovie(movieId: Int): Flow<SimpleResult<Boolean>>

    suspend fun getTotalFavoriteMovie(): Flow<SimpleResult<Int>>

    suspend fun getDetailMovies(id :Int) : Flow<SimpleResult<MovieDetailResponse>>
}