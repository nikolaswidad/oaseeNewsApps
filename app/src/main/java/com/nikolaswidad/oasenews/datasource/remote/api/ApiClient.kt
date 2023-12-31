package com.nikolaswidad.oasenews.datasource.remote.api

import com.nikolaswidad.oasenews.datasource.remote.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {
//    @GET("top-headlines")
//    suspend fun loadNews(
//        @Query("country") id: String,
//        @Query("apiKey") apiKey: String
//    ): NewsResponse
//
    @GET("news/search")
    suspend fun searchNews(
        @Query("title") title: String,
//        @Query("apiKey") apiKey: String
    ): NewsResponse

//    @GET("news/keyword")
//    suspend fun searchNews(
//        @Query("title") search: String,
////        @Query("apiKey") apiKey: String
//    ): NewsResponse

    @GET("news")
    suspend fun loadNews(
//        @Query("") news: String,
    ): NewsResponse
}