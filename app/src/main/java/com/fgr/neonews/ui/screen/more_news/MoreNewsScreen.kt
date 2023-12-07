package com.fgr.neonews.ui.screen.more_news

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.fgr.neonews.NewsCategories
import com.fgr.neonews.component.item.NewsItemLarge
import com.fgr.neonews.component.navbar.NavBarSecondary
import com.fgr.neonews.ui.theme.NeoNewsTheme

@Composable
fun MoreNewsScreen(
    navHostController: NavHostController,
    newsType: String?,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(24.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        item {
            NavBarSecondary(
                title = newsType ?: NewsCategories.Local
            ) {
                navHostController.popBackStack()
            }
        }
        items(10) {
            NewsItemLarge(
                title = "Kali Pertama Berdiri Satu Panggung, Fuji Ungkap Rasa Kagum Terhadap Lesti Kejora",
                modifier = Modifier
                    .padding(horizontal = 12.dp)
            ) {
                // TODO: On Item Clicked
            }
        }
    }
}

@Preview
@Composable
fun MoreNewsScreenPreview() {
    NeoNewsTheme {
        MoreNewsScreen(rememberNavController(), null)
    }
}