package com.nikolaswidad.oasenews.ui.screen.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.nikolaswidad.oasenews.datasource.local.entity.NewsEntity
import com.nikolaswidad.oasenews.ui.navigation.Screen
import com.nikolaswidad.oasenews.ui.screen.detail.DetailScreen
import com.nikolaswidad.oasenews.ui.theme.NewsAppComposeTheme

class MainActivity : ComponentActivity() {
    
//    private lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsAppComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
//                    NewsApp()
//                    navController = rememberNavController()
                    SetupNavGraph()
                }
            }
        }
    }
}

@Composable
fun SetupNavGraph(
    navController : NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                onNavigateDetail = {
                    navController.currentBackStackEntry?.savedStateHandle?.set(
                        key = "news", value = it
                    )
                    navController.navigate(
                        Screen.Detail.route
                    )
                },
                navController = navController
            )
        }
        composable(Screen.Bookmarks.route) {
            BookmarkScreen(
                onNavigateDetail =
                {
                navController.currentBackStackEntry?.savedStateHandle?.set(
                    key = "news", value = it
                )
                navController.navigate(
                    Screen.Detail.route)
                },
                navigateBack = {
                    navController.navigateUp()
                }
            )
        }
        composable(
            Screen.Detail.route
        ) {
            val news =
                navController.previousBackStackEntry?.savedStateHandle?.get<NewsEntity>("news")
            if (news != null) {
                DetailScreen(news = news, navigateBack = { navController.navigateUp() })
            }
        }
        composable(
            Screen.Profile.route
        ) {
            ProfileScreen(
                navigateBack = {
                    navController.navigateUp()
                }
            )
        }
    }
}


//@Composable
//fun NewsApp(
//    modifier: Modifier = Modifier,
//    navController: NavHostController = rememberNavController()
//) {
//    val navBackStackEntry by navController.currentBackStackEntryAsState()
//    val currentRoute = navBackStackEntry?.destination?.route
//
//    NavHost(
//        navController = navController,
//        startDestination = Screen.Home.route,
//    ) {
//        composable(Screen.Home.route) {
//            HomeScreen(onNavigateDetail = {
//                navController.currentBackStackEntry?.savedStateHandle?.set(
//                    key = "news", value = it
//                )
//                navController.navigate(
//                    Screen.Detail.route
//                )
//            })
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
//    Scaffold(
//        bottomBar = {
//            if (currentRoute != Screen.Detail.route) {
//                BottomBar(navController = navController)
//            }
//        }, modifier = modifier
//    ) { innerPadding ->
//        NavHost(
//            navController = navController,
//            startDestination = Screen.Home.route,
//            modifier = Modifier.padding(innerPadding)
//        ) {
//            composable(Screen.Home.route) {
//                HomeScreen(onNavigateDetail = {
//                    navController.currentBackStackEntry?.savedStateHandle?.set(
//                        key = "news", value = it
//                    )
//                    navController.navigate(
//                        Screen.Detail.route
//                    )
//                })
//            }
//            composable(Screen.Bookmarks.route) {
//                BookmarkScreen(onNavigateDetail = {
//                    navController.currentBackStackEntry?.savedStateHandle?.set(
//                        key = "news", value = it
//                    )
//                    navController.navigate(
//                        Screen.Detail.route
//                    )
//                })
//            }
//            composable(
//                Screen.Detail.route
//            ) {
//                val news =
//                    navController.previousBackStackEntry?.savedStateHandle?.get<NewsEntity>("news")
//                if (news != null) {
//                    DetailScreen(news = news, navigateBack = { navController.navigateUp() })
//                }
//            }
//            composable(Screen.Profile.route) {
//                ProfileScreen()
//            }
//        }
//
//    }
//}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NewsAppComposeTheme {
//        NewsApp()
    }
}