package com.nikolaswidad.oasenews.domain

import com.nikolaswidad.oasenews.datasource.INewsRepository
import com.example.newsappcompose.datasource.Resource
import com.nikolaswidad.oasenews.datasource.local.entity.NewsBookmarkEntity
import com.nikolaswidad.oasenews.datasource.local.entity.NewsEntity
import kotlinx.coroutines.flow.Flow

class NewsInteractor(private val newsRepository: INewsRepository) : NewsUseCase {
    override fun loadNews(): Flow<Resource<List<NewsEntity>>> = newsRepository.loadNews()

    override fun searchNews(q: String): Flow<Resource<List<NewsEntity>>> =
        newsRepository.searchNews(q)

    override suspend fun loadNewsBookmarks(): Flow<List<NewsBookmarkEntity>> =
        newsRepository.loadNewsBookmarks()

    override suspend fun checkBookmark(id: String): Flow<Boolean> = newsRepository.checkBookmark(id)

    override suspend fun addBookmarked(news: NewsBookmarkEntity) =
        newsRepository.addBookmark(news)

    override suspend fun unBookmarked(news: NewsBookmarkEntity) = newsRepository.unBookmark(news)
}