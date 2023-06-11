package com.nikolaswidad.oasenews.datasource

import com.example.newsappcompose.datasource.Resource
import com.nikolaswidad.oasenews.datasource.remote.api.ApiResponse
import kotlinx.coroutines.flow.*

abstract class NetworkBoundResource<ResultType, RequestType> {
    private var result: Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading())
        when (val apiResponse = createCall().first()) {
            is ApiResponse.Success -> {
                emitAll(load(apiResponse.data).map { Resource.Success(it) })
            }
            is ApiResponse.Error -> {
                emit(Resource.Error(apiResponse.errorMessage))
            }
            is ApiResponse.Empty -> {
                emit(Resource.Empty())
            }
        }
    }

    protected abstract suspend fun load(data: RequestType): Flow<ResultType>

    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

    fun asFlow(): Flow<Resource<ResultType>> = result
}