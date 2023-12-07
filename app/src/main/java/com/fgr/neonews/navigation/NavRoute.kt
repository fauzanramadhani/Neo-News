package com.fgr.neonews.navigation

const val A_ARGS_KEY = "a"
const val B_ARGS_KEY = "b"
const val C_ARGS_KEY = "c"
const val D_ARGS_KEY = "d"
const val E_ARGS_KEY = "e"

sealed class NavRoute (val route: String) {
    object ROOT: NavRoute(route = "root")
    object BaseScreen: NavRoute(route = "base_screen")
    object DetailScreen: NavRoute(route = "detail_screen/{$A_ARGS_KEY}") {
        fun navigateWithLocation(
            id: String
        ): String {
            return "detail_screen/$id"
        }
    }
}