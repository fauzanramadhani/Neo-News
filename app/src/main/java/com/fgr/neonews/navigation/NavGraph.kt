package com.fgr.neonews.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.fgr.neonews.ui.screen.base.BaseScreen
import com.fgr.neonews.ui.screen.detail_news.DetailNewsScreen
import com.fgr.neonews.ui.screen.more_news.MoreNewsScreen

@Composable
fun NavGraph(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        route = NavRoute.ROOT.route,
        startDestination = NavRoute.BaseScreen.route
    ) {
        composable(
            route = NavRoute.BaseScreen.route
        ) {
            BaseScreen(navHostController = navHostController)
        }
        composable(
            route = NavRoute.MoreNewsScreen.route,
            arguments = listOf(
                navArgument(A_ARGS_KEY) {
                    type = NavType.StringType
                }
            )
        ) {
            val getA = it.arguments?.getString(A_ARGS_KEY) ?: ""
            MoreNewsScreen(navHostController, getA)
        }
        composable(
            route = NavRoute.DetailScreen.route,
            arguments = listOf(
                navArgument(A_ARGS_KEY) {
                    type = NavType.StringType
                }
            )
        ) {
            val getA = it.arguments?.getString(A_ARGS_KEY)
            DetailNewsScreen(navHostController, getA)
        }
    }
}