package com.nikolaswidad.oasenews.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.nikolaswidad.oasenews.datasource.local.entity.NewsEntity
import com.nikolaswidad.oasenews.ui.screen.detail.DetailScreen
import com.nikolaswidad.oasenews.ui.screen.main.BookmarkScreen
import com.nikolaswidad.oasenews.ui.screen.main.HomeScreen
import com.nikolaswidad.oasenews.ui.screen.main.ProfileScreen

//@Composable
//fun SetupNavGraph(
//    navController : NavHostController
//) {
//    NavHost(
//        navController = navController,
//        startDestination = Screen.Home.route,
//    ) {
//        composable(Screen.Home.route) {
//            HomeScreen(
//                onNavigateDetail = {
//                navController.currentBackStackEntry?.savedStateHandle?.set(
//                    key = "news", value = it
//                )
//                navController.navigate(
//                    Screen.Detail.route
//                )
//                },
//                navController = navController
//            )
//        }
//        composable(Screen.Bookmarks.route) {
//            BookmarkScreen(onNavigateDetail = {
//                navController.currentBackStackEntry?.savedStateHandle?.set(
//                    key = "news", value = it
//                )
//                navController.navigate(
//                    Screen.Detail.route
//                )
//            })
//        }
//        composable(
//            Screen.Detail.route
//        ) {
//            val news =
//                navController.previousBackStackEntry?.savedStateHandle?.get<NewsEntity>("news")
//            if (news != null) {
//                DetailScreen(news = news, navigateBack = { navController.navigateUp() })
//            }
//        }
//        composable(Screen.Profile.route) {
//            ProfileScreen()
//        }
//    }
//}