package com.nikolaswidad.oasenews.datasource

import com.nikolaswidad.oasenews.datasource.local.entity.NewsBookmarkEntity
import com.nikolaswidad.oasenews.datasource.local.entity.NewsEntity
import kotlinx.coroutines.flow.Flow

interface INewsRepository {
    fun loadNews(): Flow<Resource<List<NewsEntity>>>
    fun searchNews(title: String): Flow<Resource<List<NewsEntity>>>
//    fun searchNews(): Flow<Resource<List<NewsEntity>>>


    suspend fun loadNewsBookmarks(): Flow<List<NewsBookmarkEntity>>
    suspend fun checkBookmark(id: String): Flow<Boolean>
    suspend fun addBookmark(news: NewsBookmarkEntity)
    suspend fun unBookmark(news: NewsBookmarkEntity)
}