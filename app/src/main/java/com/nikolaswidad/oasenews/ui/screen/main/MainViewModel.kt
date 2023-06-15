package com.nikolaswidad.oasenews.ui.screen.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikolaswidad.oasenews.datasource.Resource
import com.nikolaswidad.oasenews.datasource.local.entity.NewsBookmarkEntity
import com.nikolaswidad.oasenews.datasource.local.entity.NewsEntity
import com.nikolaswidad.oasenews.domain.NewsUseCase
import com.nikolaswidad.oasenews.utils.EspressoIdlingResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val useCase: NewsUseCase
) : ViewModel() {
    private val _news = MutableLiveData<Resource<List<NewsEntity>>>()
    val news: LiveData<Resource<List<NewsEntity>>> get() = _news

    var newsBookmarks by mutableStateOf(emptyList<NewsEntity>())

    var isBookmarked by mutableStateOf(false)

    fun loadNews() {
        EspressoIdlingResource.increment()
        viewModelScope.launch {
            useCase.loadNews().collect {
                _news.value = it
            }
            EspressoIdlingResource.decrement()
        }
    }

//    fun searchNews(title: String) {
//        EspressoIdlingResource.increment()
//        viewModelScope.launch {
//            useCase.searchNews(title).collect {
//                _news.value = it
//            }
//            EspressoIdlingResource.decrement()
//        }
//    }

    fun searchNews() {
        EspressoIdlingResource.increment()
        viewModelScope.launch {
            useCase.searchNews().collect {
                _news.value = it
            }
            EspressoIdlingResource.decrement()
        }
    }

    fun loadNewsBookmarks() {
        viewModelScope.launch {
            useCase.loadNewsBookmarks().collect {
                newsBookmarks = it.map { news ->
                    NewsEntity(
                        publishedAt = news.publishedAt,
                        author = news.author,
                        url = news.url,
                        sentiment = news.sentiment,
                        credibilityScore = news.credibilityScore,
//                    description = news.description,
                        title = news.title,
//                    urlToImage = news.urlToImage,
//                        content = news.content,
                        summarize = news.summarize,
                        id = news.id
                    )
                }
            }
        }
    }

    fun checkBookmark(id: String) {
        viewModelScope.launch {
            useCase.checkBookmark(id).collect {
                isBookmarked = it
            }
        }
    }

    fun addBookmarked(news: NewsEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.addBookmarked(
                NewsBookmarkEntity(
                    publishedAt = news.publishedAt,
                    author = news.author,
                    url = news.url,
                    sentiment = news.sentiment,
                    credibilityScore = news.credibilityScore,
//                    description = news.description,
                    title = news.title,
//                    urlToImage = news.urlToImage,
//                    content = news.content,
                    summarize = news.summarize,
                    id = news.id
                )
            )
        }
    }

    fun unBookmarked(news: NewsEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.unBookmarked(
                NewsBookmarkEntity(
                    publishedAt = news.publishedAt,
                    author = news.author,
                    url = news.url,
                    sentiment = news.sentiment,
                    credibilityScore = news.credibilityScore,
//                    description = news.description,
                    title = news.title,
//                    urlToImage = news.urlToImage,
//                    content = news.content,
                    summarize = news.summarize,
                    id = news.id
                )
            )
        }
    }
}