package com.fgr.neonews.ui.screen.base.favorite

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.fgr.neonews.component.item.NewsItemLarge
import com.fgr.neonews.component.navbar.NavBarPrimary
import com.fgr.neonews.navigation.NavRoute

@Composable
fun FavoriteScreen(
    navHostController: NavHostController,
    myPaddingValues: PaddingValues,
    contentRoute: MutableIntState
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(24.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        item {
            NavBarPrimary()
        }
        items(10) {
            NewsItemLarge(
                title = "Kali Pertama Berdiri Satu Panggung, Fuji Ungkap Rasa Kagum Terhadap Lesti Kejora",
                modifier = Modifier
                    .padding(horizontal = 12.dp)
            ) {
                navHostController.navigate(NavRoute.DetailScreen.navigateWithId("a"))
            }
        }
        item {
            Spacer(modifier = Modifier.padding(bottom = myPaddingValues.calculateBottomPadding() + 12.dp))
        }
    }
}