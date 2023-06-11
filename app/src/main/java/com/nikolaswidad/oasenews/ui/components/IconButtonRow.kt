package com.nikolaswidad.oasenews.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.nikolaswidad.oasenews.R
import com.nikolaswidad.oasenews.ui.navigation.NavigationItem
import com.nikolaswidad.oasenews.ui.navigation.Screen

//@Composable
//fun RowBar(
//    onSearch: (String) -> Unit,
//) {
//    Row(
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        SearchBar(
//            onSearch = onSearch,
//            modifier = Modifier.weight(1f)
//        )
//        IconButtonRow(navController = navController)
//    }
//
//}

@Composable
fun IconButtonRow(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(R.string.menu_bookmarks),
                icon = ImageVector.vectorResource(id = R.drawable.ic_bookmarks),
                screen = Screen.Bookmarks
            ),
            NavigationItem(
                title = stringResource(R.string.all_profile),
                icon = Icons.Default.AccountCircle,
                screen = Screen.Profile
            ),
        )

        navigationItems.forEach { item ->
            IconButton(
                onClick = {
                    navController.navigate(item.screen.route) {
//                        popUpTo(navController.graph.findStartDestination().id) {
//                            saveState = true
//                        }
//                        popUpTo(navController.graph.id) {
//                            saveState = true
//                        }
//                        restoreState = true
//                        launchSingleTop = true
                    }
                }
            ) {
                Icon(
                    imageVector = item.icon,
                    contentDescription = item.title,
                    tint = if (currentRoute == item.screen.route) {
                        MaterialTheme.colors.secondary
                    } else {
                        Color.LightGray
                    }
                )
            }
        }
    }
}

//@Preview
//@Composable
//fun RowBarPreview() {
//    RowBar(
//        onSearch = {},
//        navController = NavHostController(context = LocalContext.current)
//    )
//}



