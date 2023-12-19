package com.fgr.neonews.ui.screen.base

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.fgr.neonews.R
import com.fgr.neonews.ui.screen.favorite.FavoriteScreen
import com.fgr.neonews.ui.screen.home.HomeScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseScreen(
    navHostController: NavHostController,
) {
    val context = LocalContext.current
    val contentRoute = rememberSaveable { mutableIntStateOf(0) }

    var myPaddingValues by remember {
        mutableStateOf(PaddingValues())
    }

    // Storing Back Stack Entry
    val backStackEntry = navHostController.currentBackStackEntryAsState()

    val currentContentIndex = rememberSaveable { mutableIntStateOf(0) }

    BackHandler {
        if (contentRoute.intValue == 0) {
            (context as Activity).finish()
        } else {
            contentRoute.intValue = 0
        }
    }

    val screens = listOf(
        DashboardContent(
            title = "Home",
            icon = R.drawable.ic_home,
            content = {
                HomeScreen(
                    navHostController = navHostController,
                    myPaddingValues,
                    contentRoute
                )
            }
        ),
        DashboardContent(
            title = "Favorite",
            icon = R.drawable.ic_star,
            content = {
                FavoriteScreen(
                    navHostController = navHostController,
                    myPaddingValues,
                    contentRoute
                )
            }
        ),
    )
    val currentTheme = isSystemInDarkTheme()

    val shadowColor = if (currentTheme) {
        Color.Black.copy(alpha = 0.2f) // Dark Theme
    } else {
        Color.Black.copy(alpha = 0.02f) // Light Theme
    }
    val shadowElevation = 1.dp

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.background,
                modifier = Modifier.drawWithContent {
                    drawContent()
                    drawRect(
                        color = shadowColor,
                        topLeft = Offset(0f, size.height),
                        size = Size(size.width, shadowElevation.toPx()),
                        style = Fill
                    )
                    drawRect(
                        color = shadowColor,
                        topLeft = Offset(-shadowElevation.toPx(), -shadowElevation.toPx()),
                        size = Size(
                            size.width + shadowElevation.toPx() * 2,
                            shadowElevation.toPx() * 2
                        ),
                        style = Fill
                    )
                }
            ) {
                screens.forEachIndexed { index, item ->
                    val selected = contentRoute.intValue == index

                    val iconSelectedColor =
                        if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant

                    NavigationBarItem(
                        selected = selected,
                        colors = NavigationBarItemDefaults.colors(
                            indicatorColor = MaterialTheme.colorScheme.primaryContainer
                        ),
                        onClick = {
                            contentRoute.intValue = index
                        },
                        label = {
                            Text(
                                text = item.title,
                                style = if (selected) {
                                    typography.labelSmall
                                } else {
                                    typography.bodySmall
                                }
                            )
                        },
                        icon = {
                            Icon(
                                painterResource(item.icon),
                                tint = iconSelectedColor,
                                contentDescription = null,
                            )
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        myPaddingValues = paddingValues
        screens[contentRoute.intValue].content.invoke(myPaddingValues)
    }

    // When Back Stack Entry happens
    LaunchedEffect(backStackEntry) {
        if (backStackEntry.value?.destination?.route != null) {
            // Set the index into the position
            currentContentIndex.intValue =
                getIndexFromDestination(backStackEntry.value?.destination?.route)
        }
    }

}

private fun getIndexFromDestination(route: String?): Int {
    return when (route) {
        // Set the index position based on Back Stack Entry
//        NavRoute.SettingScreen.route -> 4
//        NavRoute.CreateVacanciesScreen.route -> 1
        else -> 0
    }
}

data class DashboardContent(
    val title: String,
    val icon: Int,
    val content: @Composable (PaddingValues) -> Unit = {}
)