package com.fgr.neonews.ui.screen.favorite

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.fgr.neonews.UiState
import com.fgr.neonews.component.button.ButtonTextIcon
import com.fgr.neonews.component.item.NewsItemLarge
import com.fgr.neonews.component.loading.SkeletonLoading
import com.fgr.neonews.component.navbar.NavBarPrimary
import com.fgr.neonews.isoToMills
import com.fgr.neonews.navigation.NavRoute
import com.fgr.neonews.truncate
import kotlinx.coroutines.launch

@Composable
fun FavoriteScreen(
    navHostController: NavHostController,
    myPaddingValues: PaddingValues,
    contentRoute: MutableIntState,
    favoriteViewModel: FavoriteViewModel = hiltViewModel()
) {
    val scope = rememberCoroutineScope()
    val favoriteNewsState by favoriteViewModel.favoriteNewsState.collectAsState()

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(24.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        item {
            NavBarPrimary()
        }
        when (val currentState = favoriteNewsState) {
            UiState.Empty -> {}
            is UiState.Error -> {
                item {
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 12.dp)
                            .background(MaterialTheme.colorScheme.surfaceVariant)
                            .fillMaxWidth()
                            .height(112.dp)
                            .padding(12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = currentState.errorMessage.truncate(10),
                            style = MaterialTheme.typography.bodyMedium
                        )
                        ButtonTextIcon(
                            text = {
                                Text(
                                    text = "Muat Ulang",
                                    style = MaterialTheme.typography.labelLarge,
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
                                    favoriteViewModel.getFavoriteNews()
                                }
                            }
                        )
                    }
                }
            }

            UiState.Loading -> {
                items(5) {
                    SkeletonLoading(
                        modifier = Modifier
                            .padding(horizontal = 12.dp)
                            .fillMaxWidth()
                            .height(112.dp)
                    )
                }
            }

            is UiState.Success -> {
                items(currentState.data) { favoriteNews ->
                    NewsItemLarge(
                        title = favoriteNews.title,
                        imageUrl = favoriteNews.imageUrl,
                        modifier = Modifier
                            .padding(horizontal = 12.dp)
                    ) {
                        navHostController.navigate(
                            NavRoute.DetailScreen.navigateWithData(
                                dateTime = favoriteNews.publishedAt.isoToMills().toString(),
                                title = favoriteNews.title,
                                source = favoriteNews.source,
                                imageUrl = Uri.encode(favoriteNews.imageUrl),
                                description = favoriteNews.description,
                                newsUrl = Uri.encode(favoriteNews.newsUrl)
                            )
                        )
                    }
                }
            }
        }
        item {
            Spacer(modifier = Modifier.padding(bottom = myPaddingValues.calculateBottomPadding() + 12.dp))
        }
    }
}