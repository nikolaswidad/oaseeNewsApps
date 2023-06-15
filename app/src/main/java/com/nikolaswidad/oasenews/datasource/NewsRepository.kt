package com.nikolaswidad.oasenews.datasource

import com.nikolaswidad.oasenews.datasource.local.LocalDataSource
import com.nikolaswidad.oasenews.datasource.local.entity.NewsBookmarkEntity
import com.nikolaswidad.oasenews.datasource.local.entity.NewsEntity
import com.nikolaswidad.oasenews.datasource.remote.RemoteDataSource
import com.nikolaswidad.oasenews.datasource.remote.api.ApiResponse
import com.nikolaswidad.oasenews.datasource.remote.model.NewsItem
import com.nikolaswidad.oasenews.utils.Utils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow

class NewsRepository(
    private val remoteDataSource: RemoteDataSource, private val localDataSource: LocalDataSource
) : INewsRepository {
    override fun loadNews(): Flow<Resource<List<NewsEntity>>> {
        return object : NetworkBoundResource<List<NewsEntity>, List<NewsItem>>() {
            override suspend fun load(data: List<NewsItem>): Flow<List<NewsEntity>> {
                return listOf(data.map {
                    NewsEntity(
                        publishedAt = it.publishedAt,
                        author = it.author,
                        url = it.url,
//                        description = it.description,
                        title = it.title,
                        sentiment = it.sentiment,
                        credibilityScore = it.credibilityScore,
//                        urlToImage = it.urlToImage,
//                        content = it.content,
                        summarize = it.summarize,
                        id = Utils.formatDateToId(it.publishedAt)
                    )
                }).asFlow()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<NewsItem>>> {
                return remoteDataSource.loadNews()
            }

        }.asFlow()
    }

//    override fun searchNews(title: String): Flow<Resource<List<NewsEntity>>> {
//        return object : NetworkBoundResource<List<NewsEntity>, List<NewsItem>>() {
//            override suspend fun load(data: List<NewsItem>): Flow<List<NewsEntity>> {
//                return listOf(data.map {
//                    NewsEntity(
//                        publishedAt = it.publishedAt,
//                        author = it.author,
//                        url = it.url,
////                        description = it.description,
//                        title = it.title,
////                        urlToImage = it.urlToImage,
//                        content = it.content,
//                        id = Utils.formatDateToId(it.publishedAt)
//                    )
//                }).asFlow()
//            }
//
//            override suspend fun createCall(): Flow<ApiResponse<List<NewsItem>>> {
//                return remoteDataSource.searchNews(title)
//            }
//
//        }.asFlow()
//    }
    override fun searchNews(): Flow<Resource<List<NewsEntity>>> {
        return object : NetworkBoundResource<List<NewsEntity>, List<NewsItem>>() {
            override suspend fun load(data: List<NewsItem>): Flow<List<NewsEntity>> {
                return listOf(data.map {
                    NewsEntity(
                        publishedAt = it.publishedAt,
                        author = it.author,
                        url = it.url,
//                        description = it.description,
                        title = it.title,
//                        urlToImage = it.urlToImage,
                        content = it.content,
                        id = Utils.formatDateToId(it.publishedAt)
                    )
                }).asFlow()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<NewsItem>>> {
                return remoteDataSource.searchNews()
            }

        }.asFlow()
    }

    override suspend fun loadNewsBookmarks(): Flow<List<NewsBookmarkEntity>> =
        localDataSource.loadNewsBookmarks()

    override suspend fun checkBookmark(id: String): Flow<Boolean> =
        localDataSource.checkBookmark(id)

    override suspend fun addBookmark(news: NewsBookmarkEntity) = localDataSource.addBookmark(news)

    override suspend fun unBookmark(news: NewsBookmarkEntity) = localDataSource.unBookmark(news)

}