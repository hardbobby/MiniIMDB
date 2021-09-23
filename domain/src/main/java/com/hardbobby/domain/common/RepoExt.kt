package com.hardbobby.domain.common

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import retrofit2.Response

/**
 * Created by Bobby.Hardian on 21/09/2021.
 */

fun <Response> SimpleResponse<Response>.mapToResult(): Flow<SimpleResult<Response>> {
    val result = when {
        this.isSuccessful -> {
            val body = this.body()
            when {
                body?.results != null -> {
                    Result.Success.Data(body.results!!)
                }
                body?.results == null -> {
                    Result.Success.NoData
                }
                else -> {
                    Result.Failure(SimpleError("Success but unknown failure"))
                }
            }
        }
        else -> Result.Failure(SimpleError(this.errorBody()?.string().orEmpty()))
    }
    return flowOf(result)
}

fun <Data> Response<Data>.mapToResponseResult(): Flow<SimpleResult<Data>> {
    val result = when {
        this.isSuccessful -> {
            val body = this.body()
            when {
                body != null -> {
                    Result.Success.Data(body)
                }
                else -> {
                    Result.Success.NoData
                }
            }
        }
        else -> Result.Failure(SimpleError(this.errorBody()?.string().orEmpty()))
    }
    return flowOf(result)
}
