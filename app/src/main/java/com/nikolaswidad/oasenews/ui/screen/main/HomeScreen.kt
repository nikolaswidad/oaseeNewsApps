package com.nikolaswidad.oasenews.ui.screen.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.newsappcompose.datasource.Resource
import com.nikolaswidad.oasenews.datasource.local.entity.NewsEntity
import com.nikolaswidad.oasenews.ui.components.GenericState
import com.nikolaswidad.oasenews.ui.components.SearchBar
import com.nikolaswidad.oasenews.ui.theme.NewsAppComposeTheme
import com.nikolaswidad.oasenews.utils.TestTag
import com.nikolaswidad.oasenews.R
import com.nikolaswidad.oasenews.ui.components.IconButtonRow
//import com.nikolaswidad.oasenews.ui.components.IconButtonRow
import com.nikolaswidad.oasenews.ui.components.NewsItemCard
import com.nikolaswidad.oasenews.ui.components.ScrollToTopButton
import com.nikolaswidad.oasenews.ui.navigation.Screen
import kotlinx.coroutines.launch
//import com.nikolaswidad.oasenews.ui.components.RowBar
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = koinViewModel(),
    onNavigateDetail: (NewsEntity) -> Unit,
    navController: NavController
) {

    val state by viewModel.news.observeAsState()

    Column {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            SearchBar(onSearch = {
                if (it.isNotEmpty()) viewModel.searchNews(it) else viewModel.loadNews()
            })
            IconButtonRow(navController = navController)
        }

        Box(modifier = modifier.fillMaxSize()) {
            when (state) {
                is Resource.Empty -> {
                    GenericState(
                        message = stringResource(id = R.string.home_search_empty),
                        drawable = R.mipmap.icon_search_empty,
                        modifier = modifier.align(Alignment.Center)
                    )
                }
                is Resource.Error -> {
                    GenericState(
                        message = state?.message.toString(),
                        drawable = R.drawable.ic_empty_news,
                        modifier = modifier.align(Alignment.Center)
                    )
                }
                is Resource.Loading -> {
                    CircularProgressIndicator(
                        modifier = modifier.align(Alignment.Center)
                    )
                }
                is Resource.Success -> {
                    NewsContent(
                        news = state?.data!!,
                        onNavigateDetail = onNavigateDetail
                    )
                }
                else -> {
                    viewModel.loadNews()
                }
            }
        }
    }
}

@Composable
fun NewsContent(
    news: List<NewsEntity>,
    onNavigateDetail: (NewsEntity) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = Modifier) {
        val scope = rememberCoroutineScope()
        val listState = rememberLazyListState()
        val showButton: Boolean by remember{
            derivedStateOf { listState.firstVisibleItemIndex > 0 }
        }

        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = modifier.testTag(TestTag.newsList)
        ) {
            items(news, key = { it }) {
                NewsItemCard(
                    news = it,
                    onClickListener = { news ->
                        onNavigateDetail(news)
                    }, modifier = Modifier.testTag(TestTag.newsItem)
                )

                Divider(thickness = 2.dp)

            }
        }
        AnimatedVisibility(
            visible = showButton,
            enter = fadeIn() + slideInVertically(),
            exit = fadeOut() + slideOutVertically(),
            modifier = Modifier
                .padding(bottom = 30.dp)
                .align(Alignment.BottomCenter)
        ) {
            ScrollToTopButton(
                onClick = {
                    scope.launch {
                        listState.scrollToItem(index = 0)
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PopularScreenPreview() {
    NewsAppComposeTheme {
        HomeScreen(onNavigateDetail = {}, navController = rememberNavController())
    }
}