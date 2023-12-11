package com.fgr.neonews.ui.screen.base.home

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.fgr.neonews.NewsCategories
import com.fgr.neonews.R
import com.fgr.neonews.UiState
import com.fgr.neonews.component.button.ButtonTextIcon
import com.fgr.neonews.component.item.NewsItemSmall
import com.fgr.neonews.component.loading.SkeletonLoading
import com.fgr.neonews.component.navbar.NavBarPrimary
import com.fgr.neonews.navigation.NavRoute
import com.fgr.neonews.truncate
import com.fgr.neonews.ui.theme.NeoNewsTheme
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(
    navHostController: NavHostController,
    myPaddingValues: PaddingValues,
    contentRoute: MutableIntState,
    homeViewModel: HomeViewModel = hiltViewModel(),
) {
    val scope = rememberCoroutineScope()
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .background(colorScheme.background)
            .padding(bottom = myPaddingValues.calculateBottomPadding() + 12.dp)
    ) {
        NavBarPrimary()
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, end = 12.dp, top = 12.dp)
        ) {
            Text(
                text = "Lokal",
                style = typography.titleMedium,
                color = colorScheme.onBackground,
            )
            Spacer(modifier = Modifier.weight(1f))
            ButtonTextIcon(
                text = {
                    Text(
                        text = "Lebih Banyak",
                        style = typography.labelMedium,
                        color = colorScheme.onBackground,
                    )
                },
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_circle),
                        contentDescription = null,
                        tint = colorScheme.onBackground
                    )
                }
            ) {
                navHostController.navigate(
                    NavRoute
                        .MoreNewsScreen
                        .navigateWithCategories(NewsCategories.Local)
                ) {
                    launchSingleTop = true
                }
            }
        }
        val localNewsState by homeViewModel.localNewsState.collectAsState()
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = 12.dp),
        ) {

            when (val currentState = localNewsState) {
                UiState.Empty -> {
                    // Nothing
                }

                is UiState.Error -> {
                    item {
                        Column(
                            modifier = Modifier
                                .background(colorScheme.surfaceVariant)
                                .width(168.dp)
                                .height(173.dp)
                                .padding(12.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = currentState.errorMessage.truncate(10),
                                style = typography.bodyMedium
                            )
                            ButtonTextIcon(
                                text = {
                                    Text(
                                        text = "Muat Ulang",
                                        style = typography.labelLarge,
                                    )
                                },
                                icon = {
                                    Icon(
                                        imageVector = Icons.Rounded.Refresh,
                                        contentDescription = null,
                                    )
                                },
                                onClick = {
                                    scope.launch {
                                        homeViewModel.getLocalNews()
                                    }
                                }
                            )
                        }

                    }
                }

                UiState.Loading -> {
                    items(3) {
                        SkeletonLoading(
                            modifier = Modifier
                                .width(168.dp)
                                .height(173.dp)
                        )
                    }
                }

                is UiState.Success -> {
                    items(currentState.data) { article ->
                        NewsItemSmall(
                            imageUrl = article.urlToImage ?: "",
                            title = article.title ?: "",
                        ) {
                            navHostController.navigate(NavRoute.DetailScreen.navigateWithId("a"))
                        }
                    }
                }
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, end = 12.dp, top = 12.dp)
        ) {
            Text(
                text = "Mancanegara",
                style = typography.titleMedium,
                color = colorScheme.onBackground,
            )
            Spacer(modifier = Modifier.weight(1f))
            ButtonTextIcon(
                text = {
                    Text(
                        text = "Lebih Banyak",
                        style = typography.labelMedium,
                        color = colorScheme.onBackground,
                    )
                },
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_circle),
                        contentDescription = null,
                        tint = colorScheme.onBackground
                    )
                }
            ) {
                navHostController.navigate(
                    NavRoute
                        .MoreNewsScreen
                        .navigateWithCategories(NewsCategories.Abroad)
                ) {
                    launchSingleTop = true
                }
            }
        }
        val abroadNewsState by homeViewModel.aboardNewsState.collectAsState()
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = 12.dp),
        ) {
            when (val currentState = abroadNewsState) {
                UiState.Empty -> {
                    // Nothing
                }

                is UiState.Error -> {
                    item {
                        Column(
                            modifier = Modifier
                                .clip(RoundedCornerShape(4.dp))
                                .background(colorScheme.surfaceVariant)
                                .width(168.dp)
                                .height(173.dp)
                                .padding(12.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = currentState.errorMessage.truncate(10),
                                style = typography.bodyMedium
                            )
                            ButtonTextIcon(
                                text = {
                                    Text(
                                        text = "Muat Ulang",
                                        style = typography.labelLarge,
                                    )
                                },
                                icon = {
                                    Icon(
                                        imageVector = Icons.Rounded.Refresh,
                                        contentDescription = null,
                                    )
                                },
                                onClick = {
                                    scope.launch {
                                        homeViewModel.getAboardNews()
                                    }
                                }
                            )
                        }
                    }
                }

                UiState.Loading -> {
                    items(3) {
                        SkeletonLoading(
                            modifier = Modifier
                                .width(168.dp)
                                .height(173.dp)
                        )
                    }
                }

                is UiState.Success -> {
                    items(currentState.data) { article ->
                        NewsItemSmall(
                            imageUrl = article.urlToImage ?: "",
                            title = article.title ?: "",
                        ) {
                            navHostController.navigate(NavRoute.DetailScreen.navigateWithId("a"))
                        }
                    }
                }
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, end = 12.dp, top = 12.dp)
        ) {
            Text(
                text = "Perang Palestina",
                style = typography.titleMedium,
                color = colorScheme.onBackground,
            )
            Spacer(modifier = Modifier.weight(1f))
            ButtonTextIcon(
                text = {
                    Text(
                        text = "Lebih Banyak",
                        style = typography.labelMedium,
                        color = colorScheme.onBackground,
                    )
                },
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_circle),
                        contentDescription = null,
                        tint = colorScheme.onBackground
                    )
                }
            ) {
                navHostController.navigate(
                    NavRoute
                        .MoreNewsScreen
                        .navigateWithCategories(NewsCategories.PalestineWar)
                ) {
                    launchSingleTop = true
                }
            }
        }
        val palestineNewsState by homeViewModel.palestineNewsState.collectAsState()
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = 12.dp),
        ) {
            when (val currentState = palestineNewsState) {
                UiState.Empty -> {
                    // Nothing
                }

                is UiState.Error -> {
                    item {
                        Column(
                            modifier = Modifier
                                .clip(RoundedCornerShape(4.dp))
                                .background(colorScheme.surfaceVariant)
                                .width(168.dp)
                                .height(173.dp)
                                .padding(12.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = currentState.errorMessage.truncate(10),
                                style = typography.bodyMedium
                            )
                            ButtonTextIcon(
                                text = {
                                    Text(
                                        text = "Muat Ulang",
                                        style = typography.labelLarge,
                                    )
                                },
                                icon = {
                                    Icon(
                                        imageVector = Icons.Rounded.Refresh,
                                        contentDescription = null,
                                    )
                                },
                                onClick = {
                                    scope.launch {
                                        homeViewModel.getPalestineNews()
                                    }
                                }
                            )
                        }
                    }
                }

                UiState.Loading -> {
                    items(3) {
                        SkeletonLoading(
                            modifier = Modifier
                                .width(168.dp)
                                .height(173.dp)
                        )
                    }
                }

                is UiState.Success -> {
                    items(currentState.data) { article ->
                        NewsItemSmall(
                            imageUrl = article.urlToImage ?: "",
                            title = article.title ?: "",
                        ) {
                            navHostController.navigate(NavRoute.DetailScreen.navigateWithId("a"))
                        }
                    }
                }
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, end = 12.dp, top = 12.dp)
        ) {
            Text(
                text = "Olahraga",
                style = typography.titleMedium,
                color = colorScheme.onBackground,
            )
            Spacer(modifier = Modifier.weight(1f))
            ButtonTextIcon(
                text = {
                    Text(
                        text = "Lebih Banyak",
                        style = typography.labelMedium,
                        color = colorScheme.onBackground,
                    )
                },
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_circle),
                        contentDescription = null,
                        tint = colorScheme.onBackground
                    )
                }
            ) {
                navHostController.navigate(
                    NavRoute
                        .MoreNewsScreen
                        .navigateWithCategories(NewsCategories.Sport)
                ) {
                    launchSingleTop = true
                }
            }
        }
        val sportNewsState by homeViewModel.sportNewsState.collectAsState()
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = 12.dp),
        ) {
            when (val currentState = sportNewsState) {
                UiState.Empty -> {
                    // Nothing
                }

                is UiState.Error -> {
                    item {
                        Column(
                            modifier = Modifier
                                .clip(RoundedCornerShape(4.dp))
                                .background(colorScheme.surfaceVariant)
                                .width(168.dp)
                                .height(173.dp)
                                .padding(12.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = currentState.errorMessage.truncate(10),
                                style = typography.bodyMedium
                            )
                            ButtonTextIcon(
                                text = {
                                    Text(
                                        text = "Muat Ulang",
                                        style = typography.labelLarge,
                                    )
                                },
                                icon = {
                                    Icon(
                                        imageVector = Icons.Rounded.Refresh,
                                        contentDescription = null,
                                    )
                                },
                                onClick = {
                                    scope.launch {
                                        homeViewModel.getSportNews()
                                    }
                                }
                            )
                        }
                    }
                }

                UiState.Loading -> {
                    items(3) {
                        SkeletonLoading(
                            modifier = Modifier
                                .width(168.dp)
                                .height(173.dp)
                        )
                    }
                }

                is UiState.Success -> {
                    items(currentState.data) { article ->
                        NewsItemSmall(
                            imageUrl = article.urlToImage ?: "",
                            title = article.title ?: "",
                        ) {
                            navHostController.navigate(NavRoute.DetailScreen.navigateWithId("a"))
                        }
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnrememberedMutableState")
@Preview
@Composable
fun HomeScreenPreview() {
    NeoNewsTheme {
        HomeScreen(
            rememberNavController(),
            PaddingValues(bottom = 24.dp),
            contentRoute = mutableIntStateOf(0)
        )
    }
}