package com.hardbobby.miniimdb.common.di

import com.hardbobby.data.remote.MovieAPI
import com.hardbobby.miniimdb.common.service.interceptor.ApiKeyInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by Bobby.Hardian on 21/09/2021.
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesLoggingInterceptor(interceptor: Interceptor): OkHttpClient {
        val httpInterceptor = HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BASIC)
        }

        return OkHttpClient.Builder()
            .addInterceptor(httpInterceptor)
            .addInterceptor(interceptor)
            .connectTimeout(25, TimeUnit.SECONDS)
            .callTimeout(25, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun providesRetrofitBuilder(okHttpClient: OkHttpClient): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
    }

    @Provides
    @Singleton
    fun providesApiKeyInterceptor(): Interceptor {
        return ApiKeyInterceptor("Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJmZTY1YjhhNWNhOWFjMTNmYTQyMjkxNmQ5NWE5YzM5ZCIsInN1YiI6IjVlOTdkZjY5MTlhYjU5MDAxNDQ2Y2E0YiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.FSvpipQB-aB8py7fCwpANX91vqVvnMIRG5Bm9h-5sCs")
    }


    @Singleton
    @Provides
    fun providesMovieAPI(retrofit: Retrofit.Builder): MovieAPI {
        return retrofit.build().create(MovieAPI::class.java)
    }
}