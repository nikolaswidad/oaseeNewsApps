package com.nikolaswidad.oasenews.datasource.remote.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

//@Parcelize
//data class NewsItem(
//
//    @field:SerializedName("publishedAt")
//    val publishedAt: String?,
//
//    @field:SerializedName("author")
//    val author: String?,
//
//    @field:SerializedName("urlToImage")
//    val urlToImage: String?,
//
//    @field:SerializedName("description")
//    val description: String?,
//
//    @field:SerializedName("title")
//    val title: String?,
//
//    @field:SerializedName("url")
//    val url: String?,
//
//    @field:SerializedName("content")
//    val content: String?
//) : Parcelable
@Parcelize
data class NewsItem(

    @field:SerializedName("title")
    val title: String?,

    @field:SerializedName("author")
    val author: String?,

    @field:SerializedName("timestamp")
    val publishedAt: String?,

//    @field:SerializedName("urlToImage")
//    val urlToImage: String?,

    @field:SerializedName("sentiment")
    val sentiment: String?,

    @field:SerializedName("url")
    val url: String?,

    @field:SerializedName("summarize")
    val content: String?
) : Parcelable