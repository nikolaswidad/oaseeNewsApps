package com.nikolaswidad.oasenews.ui.components

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.nikolaswidad.oasenews.R
import com.nikolaswidad.oasenews.ui.navigation.NavigationItem
import com.nikolaswidad.oasenews.ui.navigation.Screen

@Composable
fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.background,
        contentColor = MaterialTheme.colors.primary,
        modifier = modifier
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(R.string.menu_home),
                icon = Icons.Default.Home,
                screen = Screen.Home
            ),
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
        BottomNavigation {
            navigationItems.map {
                BottomNavigationItem(
                    icon = {
                        Icon(
                            imageVector = it.icon, contentDescription = it.title
                        )
                    },
                    selected = currentRoute == it.screen.route,
                    unselectedContentColor = Color.LightGray,
                    selectedContentColor = MaterialTheme.colors.secondary,
                    onClick = {
                        navController.navigate(it.screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    })
            }
        }
    }
}