package com.hardbobby.data.remote

import com.hardbobby.data.common.Constants.QUERY_PARAM_PAGE
import com.hardbobby.data.common.Constants.QUERY_PARAM_PATH_MOVIE
import com.hardbobby.domain.common.SimpleResponse
import com.hardbobby.domain.dto.response.BaseGenreResponse
import com.hardbobby.domain.dto.response.MovieDetailResponse
import com.hardbobby.domain.dto.response.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Bobby.Hardian on 21/09/2021.
 */
interface MovieAPI {

    @GET("movie/top_rated")
    suspend fun getTopRatedMovieList(
        @Query(QUERY_PARAM_PAGE) pageNum: Int,
    ): SimpleResponse<List<MovieResponse>>

    @GET("movie/popular")
    suspend fun getPopularMovieList(
        @Query(QUERY_PARAM_PAGE) pageNum: Int,
    ): SimpleResponse<List<MovieResponse>>

    @GET("genre/movie/list")
    suspend fun getGenreMovieList(): Response<BaseGenreResponse>

    @GET("movie/{$QUERY_PARAM_PATH_MOVIE}?append_to_response=videos,credits,reviews\"")
    suspend fun getMovieDetail(
        @Path(QUERY_PARAM_PATH_MOVIE) movieId: Int,
    ): Response<MovieDetailResponse>
}