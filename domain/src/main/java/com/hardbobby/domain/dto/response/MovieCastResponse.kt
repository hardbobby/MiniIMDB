package com.hardbobby.domain.dto.response

import com.google.gson.annotations.SerializedName

/**
 * Created by Bobby.Hardian on 22/09/2021.
 */
data class MovieCastResponse(
    @field:SerializedName("original_name")
    val originalName:String,
    @field:SerializedName("profile_path")
    val profileUrl:String?,
)
