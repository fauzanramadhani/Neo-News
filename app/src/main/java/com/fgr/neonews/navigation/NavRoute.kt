package com.fgr.neonews.navigation

const val A_ARGS_KEY = "a"
const val B_ARGS_KEY = "b"
const val C_ARGS_KEY = "c"
const val D_ARGS_KEY = "d"
const val E_ARGS_KEY = "e"
const val F_ARGS_KEY = "F"

sealed class NavRoute (val route: String) {
    object ROOT: NavRoute(route = "root")
    object BaseScreen: NavRoute(route = "base_screen")
    object MoreNewsScreen: NavRoute(route = "more_news_screen/{$A_ARGS_KEY}") {
        fun navigateWithCategories(
            categories: String
        ): String {
            return "more_news_screen/$categories"
        }
    }
    object DetailScreen: NavRoute(route = "detail_screen/{$A_ARGS_KEY}/{$B_ARGS_KEY}/{$C_ARGS_KEY}/{$D_ARGS_KEY}/{$E_ARGS_KEY}/{$F_ARGS_KEY}") {
        fun navigateWithData(
            dateTime: String,
            title: String,
            source: String,
            imageUrl: String,
            description: String,
            newsUrl: String,
        ): String {
            return "detail_screen/$dateTime/$title/$source/$imageUrl/$description/$newsUrl"
        }
    }
}