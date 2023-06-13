package com.nikolaswidad.oasenews.ui.screen.detail

import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.nikolaswidad.oasenews.R
import com.nikolaswidad.oasenews.datasource.local.entity.NewsEntity
import com.nikolaswidad.oasenews.ui.components.TopBar
import com.nikolaswidad.oasenews.ui.theme.Typography
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DetailScreen(
    news: NewsEntity,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val sheetState = rememberBottomSheetState(
        initialValue = BottomSheetValue.Collapsed)

    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = sheetState
    )
    val scope = rememberCoroutineScope()

    Column(modifier = modifier) {
        TopBar(
            title = stringResource(R.string.all_news),
            news = news,
            isBackVisible = true,
            isBookmarkVisible = true,
            onBackClick = { navigateBack() })

        BottomSheetScaffold(
            scaffoldState = scaffoldState,

            sheetContent = {
                MyBottomSheet()
//                Box(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(horizontal = 10.dp)
//                        .height(300.dp),
//                    contentAlignment = Alignment.Center
//                ) {
//                    Text(
//                        text = news.title.toString(),
//                        style = Typography.body2
//                    )
//                }
            },
            sheetBackgroundColor = Color.White
        ) {

//            FloatingActionButton(onClick = {
//                scope.launch {
//                    if (sheetState.isCollapsed) {
//                        sheetState.expand()
//                    } else {
//                        sheetState.collapse()
//                    }
//                }
//            }) {
//                Text(text = "Toggle Sheet")
//            }

            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                DetailContent(news)
            }
        }
    }
}

//Design for sheet
@Composable
fun MyBottomSheet() {
    Column(
        modifier = Modifier
            .heightIn(min = 150.dp, max = 800.dp)//This will set the max height
            .fillMaxSize()//Do this to make sheet expandable
            .background(Color.Black.copy(0.2f))
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(//Using this to create a kind of Icon that tells the user that the sheet is expandable
            modifier = Modifier
                .height(3.dp)
                .width(70.dp)
                .background(Color.White)
        )
        Spacer(//Another spacer to add a space
            modifier = Modifier
                .height(20.dp)
        )
        Text(text = "Bottom Sheet")
    }
}

@Composable
fun DetailContent(
    news: NewsEntity,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        AndroidView(factory = {
            WebView(it).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
                )
                webViewClient = WebViewClient()
            }
        }, update = {
            it.loadUrl(news.url ?: "")
        })
    }
}

@Preview
@Composable
fun DetailScreenPreview() {
    val news = NewsEntity(
        id = "1",
        title = "Sample News",
        // Provide other necessary properties for the news
    )
    DetailScreen(news = news, navigateBack = {})
}