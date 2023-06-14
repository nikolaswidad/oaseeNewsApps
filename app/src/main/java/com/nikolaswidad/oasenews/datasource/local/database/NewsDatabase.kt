package com.nikolaswidad.oasenews.datasource.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nikolaswidad.oasenews.datasource.local.entity.NewsBookmarkEntity

@Database(
    entities = [NewsBookmarkEntity::class],
    version = 3,
    exportSchema = false
)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}