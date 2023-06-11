package com.nikolaswidad.oasenews.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Bookmarks : Screen("bookmarks")
    object Profile : Screen("profile")
    object Detail : Screen("detail")
}