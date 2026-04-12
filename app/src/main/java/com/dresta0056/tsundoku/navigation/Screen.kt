package com.dresta0056.tsundoku.navigation

sealed class Screen(val route: String) {
    data object Home: Screen("mainScreen")
    data object About: Screen("AboutScreen")
    data object AddBook: Screen("AddBook")
}
