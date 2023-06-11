package com.nikolaswidad.oasenews.datasource.remote.api

import com.nikolaswidad.oasenews.datasource.remote.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {
    @GET("top-headlines")
    suspend fun loadNews(
        @Query("country") id: String,
        @Query("apiKey") apiKey: String
    ): NewsResponse

    @GET("everything")
    suspend fun searchNews(
        @Query("q") q: String,
        @Query("apiKey") apiKey: String
    ): NewsResponse
}