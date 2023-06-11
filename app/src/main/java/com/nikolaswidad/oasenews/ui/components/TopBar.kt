package com.nikolaswidad.oasenews.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.nikolaswidad.oasenews.datasource.local.entity.NewsEntity
import com.nikolaswidad.oasenews.ui.screen.main.MainViewModel
import com.nikolaswidad.oasenews.ui.theme.Typography
import com.nikolaswidad.oasenews.utils.TestTag
import org.koin.androidx.compose.koinViewModel
import com.nikolaswidad.oasenews.R

@Composable
fun TopBar(
    title: String,
    news: NewsEntity?,
    isBackVisible: Boolean,
    isBookmarkVisible: Boolean,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = koinViewModel()
) {
    TopAppBar(backgroundColor = MaterialTheme.colors.surface) {
        Row(horizontalArrangement = Arrangement.Center, modifier = modifier.fillMaxWidth()) {
            if (isBackVisible) {
                IconButton(onClick = {
                    onBackClick()
                }, modifier = modifier.testTag(TestTag.backButton)) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack, stringResource(
                            id = R.string.all_back
                        )
                    )
                }
            }
            Text(
                text = news?.title ?: title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = Typography.subtitle1,
                textAlign = TextAlign.Center,
                modifier = modifier
                    .padding(vertical = dimensionResource(id = R.dimen.all_medium))
                    .weight(1F)
            )
            if (isBookmarkVisible) {
                if (news != null) {
                    viewModel.checkBookmark(news.id)
                    IconButton(onClick = {
                        if (viewModel.isBookmarked) {
                            viewModel.unBookmarked(news = news)
                        } else {
                            viewModel.addBookmarked(news = news)
                        }
                    }, modifier = modifier.testTag(TestTag.bookmarkButton)) {
                        Icon(
                            imageVector = if (viewModel.isBookmarked) ImageVector.vectorResource(id = R.drawable.ic_bookmark_solid) else ImageVector.vectorResource(
                                id = R.drawable.ic_bookmark_border
                            ), contentDescription = stringResource(
                                id = R.string.menu_bookmarks
                            )
                        )
                    }
                }
            }
        }
    }
}