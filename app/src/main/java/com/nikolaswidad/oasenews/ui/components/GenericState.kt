package com.nikolaswidad.oasenews.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nikolaswidad.oasenews.ui.theme.NewsAppComposeTheme
import com.nikolaswidad.oasenews.R

@Composable
fun GenericState(
    message: String,
    drawable: Int,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier.padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = drawable),
            contentDescription = stringResource(id = R.string.generic_empty),
            modifier = Modifier
                .fillMaxWidth()
                .size(dimensionResource(id = R.dimen.image_height))
        )
        Text(
            text = message,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GenericStatePreview() {
    NewsAppComposeTheme {
        GenericState(
            message = stringResource(id = R.string.home_search_empty),
            drawable = R.mipmap.icon_search_empty
        )
    }
}