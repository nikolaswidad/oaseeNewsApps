package com.nikolaswidad.oasenews.datasource.remote

import com.nikolaswidad.oasenews.datasource.remote.api.ApiClient
import com.nikolaswidad.oasenews.datasource.remote.api.ApiResponse
import com.nikolaswidad.oasenews.utils.Config.API_KEY
import com.nikolaswidad.oasenews.utils.Config.COUNTRY_ID
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiClient: ApiClient) {

    suspend fun loadNews() =
        flow {
            try {
                val response = apiClient.loadNews("news").articles
                if (response.isNullOrEmpty()) {
                    emit(ApiResponse.Empty)
                } else {
                    emit(ApiResponse.Success(response))
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)

//    suspend fun searchNews(q: String) =
//        flow {
//            try {
//                val response = apiClient.searchNews(q, API_KEY).articles
//                if (response.isNullOrEmpty()) {
//                    emit(ApiResponse.Empty)
//                } else {
//                    emit(ApiResponse.Success(response))
//                }
//            } catch (e: Exception) {
//                emit(ApiResponse.Error(e.toString()))
//            }
//        }.flowOn(Dispatchers.IO)
}