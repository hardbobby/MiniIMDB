package com.hardbobby.data.common

import com.hardbobby.domain.common.response.BaseResponse
import retrofit2.Response

typealias SimpleResponse<T> = Response<BaseResponse<T>>