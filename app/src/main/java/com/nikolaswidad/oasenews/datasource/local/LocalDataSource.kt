package com.nikolaswidad.oasenews.datasource.local

import com.nikolaswidad.oasenews.datasource.local.database.NewsDao
import com.nikolaswidad.oasenews.datasource.local.entity.NewsBookmarkEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalDataSource(private val newsDao: NewsDao) {

    fun loadNewsBookmarks(): Flow<List<NewsBookmarkEntity>> = newsDao.loadNewsBookmarks()

    fun checkBookmark(id: String): Flow<Boolean> = newsDao.checkBookmark(id).map { it.isNotEmpty() }

    fun addBookmark(news: NewsBookmarkEntity) = newsDao.addBookmark(news)

    fun unBookmark(news: NewsBookmarkEntity) = newsDao.unBookmark(news)
}