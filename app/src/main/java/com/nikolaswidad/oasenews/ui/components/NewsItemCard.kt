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
                AsyncImage(
                    model = news.urlToImage,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(130.dp)
                        .clip(Shapes.small)

                )
            }
            Spacer(
                modifier = Modifier
                    .height(height = 8.dp)
            )

        }
    }
}

//@Preview
//@Composable
//fun NewsItemCardPreview() {
//    val news = NewsEntity(
//        title = "NTT Optimistis Persentase Anak Stunting Turun di Bawah Target Nasional",
//        timestamp = "2020-05-17T22:55:00.000Z",
//        sentiment = "positive",
//        url = "http://lestari.kompas.com/read/2023/05/17/225548386/ntt-optimistis-persentase-anak-stunting-turun-di-bawah-target-nasional",
//        summarize = "Bila dibandingkan laporan anak stunting tersebut berbeda dengan Survei Status Gizi Indonesia SSGI 2022 yaitu sebesar 354 persen. Ruth menyampaikan data stunting yang digunakan oleh Pemerintah Provinsi NTT adalah ePPGBM karena didasarkan pada sensus bukan survei. Kepala Dinas Kesehatan Kependudukan dan Pencatatan Sipil Provinsi NTT Ruth D Laiskodat mengatakan persentase anak stunting di NTT hingga Februari 2023 adalah 157 persen atau 67.538 anak",
//    )
//
//
//    NewsItemCard(
//        title = article.title.toString(),
//        url = article.url.toString(),
//        timestamp = article.timestamp.toString(),
//        90,
//        sentiment = article.sentiment.toString(),
//        false,
//        article = article
//    )
//}