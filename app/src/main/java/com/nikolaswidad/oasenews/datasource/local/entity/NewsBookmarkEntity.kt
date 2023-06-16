package com.nikolaswidad.oasenews.datasource.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "news_bookmark_entities")
class NewsBookmarkEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "publishedAt")
    val publishedAt: String? = null,

    @ColumnInfo(name = "author")
    val author: String? = null,

    @ColumnInfo(name = "urlToImage")
    val urlToImage: String? = null,
//    @ColumnInfo(name = "description")
//    val description: String? = null,
    @ColumnInfo(name = "title")
    val title: String? = null,

    @ColumnInfo(name = "url")
    val url: String? = null,

    @ColumnInfo(name = "sentiment")
    val sentiment: String? = null,

    @ColumnInfo(name = "credibilityScore")
    val credibilityScore: Int? = null,
//
//    @ColumnInfo(name = "content")
//    val content: String? = null,

    @ColumnInfo(name = "summarize")
    val summarize: String? = null,
) : Parcelable