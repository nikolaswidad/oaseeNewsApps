package com.nikolaswidad.oasenews.datasource.remote.model

import android.os.Message
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.nikolaswidad.oasenews.datasource.remote.model.NewsItem
import kotlinx.parcelize.Parcelize

//@Parcelize
//data class NewsResponse(
//
//    @field:SerializedName("totalResults")
//    val totalResults: Int?,
//
//    @field:SerializedName("articles")
//    val articles: List<NewsItem>?,
//
//    @field:SerializedName("status")
//    val status: String?
//) : Parcelable
@Parcelize
data class NewsResponse(
    @field:SerializedName("status")
    val status: String?,

    @field:SerializedName("message")
    val message: String?,

    @field:SerializedName("totalResult")
    val totalResult: Int?,

    @field:SerializedName("data")
    val articles: List<NewsItem>?,

) : Parcelable