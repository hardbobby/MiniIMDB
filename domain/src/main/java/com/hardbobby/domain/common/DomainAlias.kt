package com.hardbobby.domain.common

import com.hardbobby.domain.common.response.BaseResponse
import retrofit2.Response

/**
 * Created by Bobby.Hardian on 21/09/2021.
 */

typealias SimpleResult<T> = Result<T, SimpleError>

typealias SimpleResponse<T> = Response<BaseResponse<T>>

