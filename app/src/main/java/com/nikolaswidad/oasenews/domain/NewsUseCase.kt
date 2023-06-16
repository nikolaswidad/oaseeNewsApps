package com.nikolaswidad.oasenews.domain

import com.nikolaswidad.oasenews.datasource.Resource
import com.nikolaswidad.oasenews.datasource.local.entity.NewsBookmarkEntity
import com.nikolaswidad.oasenews.datasource.local.entity.NewsEntity
import kotlinx.coroutines.flow.Flow

interface NewsUseCase {
    fun loadNews(): Flow<Resource<List<NewsEntity>>>
    fun searchNews(title: String): Flow<Resource<List<NewsEntity>>>
//    fun searchNews(): Flow<Resource<List<NewsEntity>>>

    suspend fun loadNewsBookmarks(): Flow<List<NewsBookmarkEntity>>
    suspend fun checkBookmark(id: String): Flow<Boolean>
    suspend fun addBookmarked(news: NewsBookmarkEntity)
    suspend fun unBookmarked(news: NewsBookmarkEntity)
}