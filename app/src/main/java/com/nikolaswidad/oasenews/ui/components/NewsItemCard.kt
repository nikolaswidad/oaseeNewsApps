package com.nikolaswidad.oasenews.ui.components

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.nikolaswidad.oasenews.R
import com.nikolaswidad.oasenews.datasource.local.entity.NewsEntity
import com.nikolaswidad.oasenews.ui.theme.Shapes
import com.nikolaswidad.oasenews.ui.theme.Typography
import com.nikolaswidad.oasenews.utils.Utils

@Composable
fun NewsItemCard(
    news: NewsEntity,
    onClickListener: (NewsEntity) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClickListener(news) }
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 15.dp)
        ) {
            // Row News
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 8.dp)
                        .weight(1.0f)
                ) {
                    Text(
                        text = news.title.toString(),
                        overflow = TextOverflow.Ellipsis,
                        style = Typography.subtitle1,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(height = 100.dp)
                    )

                    Spacer(
                        modifier = Modifier
                            .height(height = 10.dp)
                    )

                    Text(
                        text = news.publishedAt.toString(),
//                        text = Utils.formatDateToId(news.publishedAt.toString()),
//                        text = timestamp,
                        maxLines = 1,
                        fontWeight = FontWeight.Light
                    )

                }
//                AsyncImage(
//                    model = news.urlToImage,
//                    contentDescription = null,
//                    contentScale = ContentScale.Crop,
//                    modifier = Modifier
//                        .size(130.dp)
//                        .clip(Shapes.small)
//                )
            }
            Spacer(
                modifier = Modifier
                    .height(height = 8.dp)
            )
            // Kolom Fitur
            RowFeatures(news)
        }
    }
}


@Composable
fun RowFeatures(news: NewsEntity) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        // Row Sentiment Start
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = when (news.sentiment) {
                    "positive" -> painterResource(R.drawable.ic_sentiment_positive)
                    "neutral" -> painterResource(R.drawable.ic_sentiment_neutral)
                    else -> painterResource(R.drawable.ic_sentiment_negative)
                },
                contentDescription = null,
                modifier = Modifier

            )
            Spacer(
                modifier = Modifier
                    .width(width = 2.dp)
            )
            Text(
                text = stringResource(R.string.tv_sentiment),
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
            )
            Spacer(
                modifier = Modifier
                    .width(width = 2.dp)
            )
            Icon(
                painter = painterResource(R.drawable.ic_info),
                contentDescription = "info",
                modifier = Modifier
                    .size(size = 12.dp)
            )
        }
        // Row Sentiment End

        // Row Verified Start
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                //                    painter = painterResource(R.drawable.ic_credibility_verified),

                painter = if (news.credibilityScore!! < 80)
                    painterResource(R.drawable.ic_credibility_warning)
                else
                    painterResource(R.drawable.ic_credibility_verified),
                contentDescription = null
            )
            Spacer(
                modifier = Modifier
                    .width(width = 2.dp)
            )
            Text(
                text = "${news.credibilityScore}%",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = if (news.credibilityScore!! < 80) Color(0xffffc107) else Color(0xff007bff)
            )
            Spacer(
                modifier = Modifier
                    .width(width = 2.dp)
            )
            Column {
                Text(
                    text = stringResource(R.string.tv_accurate_score),
                    fontSize = 7.sp,
                    lineHeight = 8.sp
                )
            }
        }
        // Row Verified End

//                Image(
//                    painter = if (bookmarked)
//                        painterResource(R.drawable.ic_bookmark_solid)
//                    else
//                        painterResource(R.drawable.ic_bookmark_border),
//                    contentDescription = null
//                )

    }
}


@Preview
@Composable
fun NewsItemCardPreview() {
    val news = NewsEntity(
        id = "1",
        title = "NTT Optimistis Persentase Anak Stunting Turun di Bawah Target Nasional",
        publishedAt = "2020-05-17T22:55:00.000Z",
        sentiment = "positive",
        credibilityScore = 97,
        url = "http://lestari.kompas.com/read/2023/05/17/225548386/ntt-optimistis-persentase-anak-stunting-turun-di-bawah-target-nasional",
    )


    NewsItemCard(
        news, {}
    )
}