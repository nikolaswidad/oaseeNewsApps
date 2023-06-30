package com.nikolaswidad.oasenews.ui.screen.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.nikolaswidad.oasenews.R
import com.nikolaswidad.oasenews.datasource.local.entity.NewsEntity
import com.nikolaswidad.oasenews.ui.components.GenericState
import com.nikolaswidad.oasenews.ui.components.TopBar
import org.koin.androidx.compose.koinViewModel

@Composable
fun BookmarkScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = koinViewModel(),
    onNavigateDetail: (NewsEntity) -> Unit,
    navigateBack: () -> Unit,
) {
    viewModel.loadNewsBookmarks()
    val news = viewModel.newsBookmarks
    Column {
        TopBar(title = stringResource(id = R.string.menu_bookmarks),
            news = null,
            isBackVisible = true,
            isBookmarkVisible = false,
            onBackClick = { navigateBack() })
        Box(modifier = modifier.fillMaxSize()) {
            if (news.isEmpty()) {
                GenericState(
                    message = stringResource(id = R.string.bookmark_empty),
                    drawable = R.drawable.ic_empty_news,
                    modifier = modifier.align(Alignment.Center)
                )
            } else {
                NewsContent(
                    news = news,
                    onNavigateDetail = onNavigateDetail
                )
            }
        }
    }
}