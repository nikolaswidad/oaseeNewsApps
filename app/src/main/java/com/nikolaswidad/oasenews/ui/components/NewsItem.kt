package com.nikolaswidad.oasenews.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.nikolaswidad.oasenews.R
import com.nikolaswidad.oasenews.datasource.local.entity.NewsEntity
import com.nikolaswidad.oasenews.ui.theme.NewsAppComposeTheme
import com.nikolaswidad.oasenews.ui.theme.Shapes
import com.nikolaswidad.oasenews.ui.theme.Typography
import com.nikolaswidad.oasenews.utils.DataDummy

@Composable
fun NewsItem(
    news: NewsEntity,
    onClickListener: (NewsEntity) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .shadow(dimensionResource(id = R.dimen.all_small))
            .clip(Shapes.small)
            .clickable { onClickListener(news) }) {
        Column {
            AsyncImage(
                model = news.urlToImage,
                contentDescription = news.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .align(CenterHorizontally)
                    .fillMaxWidth()
                    .padding(bottom = dimensionResource(id = R.dimen.all_small))
            )
            Text(
                text = news.title ?: "",
                style = Typography.subtitle1,
                modifier = Modifier
                    .padding(
                        start = dimensionResource(id = R.dimen.all_medium),
                        end = dimensionResource(id = R.dimen.all_medium),
                        bottom = dimensionResource(id = R.dimen.all_small)
                    )
                    .fillMaxWidth()
            )
        }
    }
}

@Preview
@Composable
fun NewsItemPreview() {
    NewsAppComposeTheme {
        NewsItem(news = DataDummy.newsDummy, onClickListener = {})
    }

}