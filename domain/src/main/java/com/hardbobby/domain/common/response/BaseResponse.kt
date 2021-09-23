package com.hardbobby.domain.common.response

import com.google.gson.annotations.SerializedName

/**
 * Created by Bobby.Hardian on 21/09/2021.
 */
data class BaseResponse<T>(
    var page: Int? = 0,
    @field:SerializedName("results")
    var results: T? = null,
    @field:SerializedName("total_pages")
    var totalPages: Int? = 0,
    @field:SerializedName("total_results")
    var totalResults: Int? = 0,
)