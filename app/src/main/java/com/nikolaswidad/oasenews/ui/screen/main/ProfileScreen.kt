package com.nikolaswidad.oasenews.ui.screen.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nikolaswidad.oasenews.R
import com.nikolaswidad.oasenews.ui.components.TopBar
import com.nikolaswidad.oasenews.ui.theme.NewsAppComposeTheme
import com.nikolaswidad.oasenews.ui.theme.Typography

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    Column {
        TopBar(
            title = stringResource(id = R.string.all_profile),
            news = null,
            isBackVisible = false,
            isBookmarkVisible = false,
            onBackClick = { })
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Image(
                painter = painterResource(id = R.mipmap.icon_profile),
                contentDescription = stringResource(id = R.string.all_profile),
                modifier = Modifier
                    .clip(CircleShape)
                    .border(width = 1.dp, color = Color.Gray, shape = CircleShape)
                    .size(250.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Text(
                text = stringResource(id = R.string.profile_name),
                style = Typography.subtitle1,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        bottom = dimensionResource(
                            id = R.dimen.all_small
                        ), top = dimensionResource(id = R.dimen.all_medium)
                    )
            )
            Text(
                text = stringResource(id = R.string.profile_email),
                style = Typography.body1,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        bottom = dimensionResource(
                            id = R.dimen.all_small
                        )
                    )
            )
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun ProfileScreenPreview() {
    NewsAppComposeTheme {
        ProfileScreen()
    }
}