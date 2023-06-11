package com.nikolaswidad.oasenews.datasource.local.database

import androidx.room.*
import com.nikolaswidad.oasenews.datasource.local.entity.NewsBookmarkEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {
    @Query("SELECT * FROM news_bookmark_entities")
    fun loadNewsBookmarks(): Flow<List<NewsBookmarkEntity>>

    @Query("SELECT * FROM news_bookmark_entities WHERE id = :id")
    fun checkBookmark(id: String): Flow<List<NewsBookmarkEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addBookmark(news: NewsBookmarkEntity)

    @Delete
    fun unBookmark(news: NewsBookmarkEntity)
}