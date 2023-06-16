package com.nikolaswidad.oasenews.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nikolaswidad.oasenews.R
import com.nikolaswidad.oasenews.ui.theme.NewsAppComposeTheme
import com.nikolaswidad.oasenews.ui.theme.Shapes
import com.nikolaswidad.oasenews.utils.TestTag

@Composable
fun SearchBar(
    onSearch: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var query by remember {
        mutableStateOf("")
    }
    TextField(
        value = query,
        onValueChange = {
            query = it
        },
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = null)
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(
            onSearch = { onSearch(query) }
        ),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.surface,
            disabledIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        placeholder = {
            Text(text = stringResource(R.string.home_search_placeholder))
        },
        modifier = modifier
            .padding(dimensionResource(id = R.dimen.all_medium))
//            .fillMaxWidth()
            .heightIn(min = dimensionResource(id = R.dimen.search_height))
            .clip(Shapes.large)
//            .background(color = Color.DarkGray, shape = Shapes.medium)
            .border(width = 1.dp, color = Color.Gray, shape = Shapes.large)
            .testTag(TestTag.searchBar)
    )
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun SearchBarPreview() {
    NewsAppComposeTheme {
        SearchBar(onSearch = {})
    }
}