package com.fgr.neonews.ui.screen.base.home

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.fgr.neonews.NewsCategories
import com.fgr.neonews.R
import com.fgr.neonews.component.button.ButtonTextIcon
import com.fgr.neonews.component.item.NewsItemSmall
import com.fgr.neonews.component.navbar.NavBarPrimary
import com.fgr.neonews.navigation.NavRoute
import com.fgr.neonews.ui.theme.NeoNewsTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    navHostController: NavHostController,
    myPaddingValues: PaddingValues,
    contentRoute: MutableIntState
) {
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
                .padding(start = 12.dp, end = 12.dp,  top = 12.dp)
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
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = 12.dp),
        ) {
            items(3) {
                NewsItemSmall(
                    imageUrl = "",
                    title = "Kali Pertama Berdiri Satu Panggung, Fuji Ungkap Rasa Kagum Terhadap Lesti Kejora"
                )
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, end = 12.dp,  top = 12.dp)
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
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = 12.dp),
        ) {
            items(3) {
                NewsItemSmall(
                    imageUrl = "",
                    title = "Kali Pertama Berdiri Satu Panggung, Fuji Ungkap Rasa Kagum Terhadap Lesti Kejora"
                )
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, end = 12.dp,  top = 12.dp)
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
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = 12.dp),
        ) {
            items(3) {
                NewsItemSmall(
                    imageUrl = "",
                    title = "Kali Pertama Berdiri Satu Panggung, Fuji Ungkap Rasa Kagum Terhadap Lesti Kejora"
                )
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, end = 12.dp,  top = 12.dp)
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
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = 12.dp),
        ) {
            items(3) {
                NewsItemSmall(
                    imageUrl = "",
                    title = "Kali Pertama Berdiri Satu Panggung, Fuji Ungkap Rasa Kagum Terhadap Lesti Kejora"
                )
            }
        }
    }
}

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