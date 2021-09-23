package com.hardbobby.miniimdb.common.service.interceptor

import com.hardbobby.data.common.Constants.PARAM_API_KEY
import okhttp3.Interceptor
import okhttp3.Response


class ApiKeyInterceptor(private val apiKey: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            chain.request()
                .newBuilder()
                .header(PARAM_API_KEY, apiKey)
                .build()
        )
    }
}