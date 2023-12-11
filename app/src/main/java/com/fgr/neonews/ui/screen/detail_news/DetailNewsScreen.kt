package com.fgr.neonews.ui.screen.detail_news

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.fgr.neonews.R
import com.fgr.neonews.component.button.ButtonTextIcon
import com.fgr.neonews.component.fab.FabPrimary
import com.fgr.neonews.component.navbar.NavBarSecondary
import com.fgr.neonews.ui.theme.NeoNewsTheme

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalMaterial3Api::class)
@Composable
fun DetailNewsScreen(
    navHostController: NavHostController,
    id: String? = null,
) {
    var isFavorite by rememberSaveable {
        mutableStateOf(false)
    }
    Scaffold(
        topBar = {
            NavBarSecondary(
                title = "Detail Berita"
            )
        },
        floatingActionButton = {
            FabPrimary(
                icon = if (isFavorite) {
                    R.drawable.ic_star
                } else {
                    R.drawable.ic_start_outlined
                }
            ) {
                isFavorite = !isFavorite
            }
        }
    ) { paddingValues ->
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .fillMaxSize()
                .background(colorScheme.background)
                .padding(
                    top = paddingValues.calculateTopPadding() + 24.dp,
                    start = 12.dp,
                    end = 12.dp,
                    bottom = 12.dp
                )
        ) {
            Text(
                text = "Sabtu, 14 Oktober 2023",
                style = typography.bodySmall,
                color = colorScheme.onSurfaceVariant
            )
            Text(
                text = "Kali Pertama Berdiri Satu Panggung, Fuji Ungkap Rasa Kagum Terhadap Lesti Kejora",
                style = typography.titleLarge,
                color = colorScheme.onBackground
            )
            Text(
                text = "CNN Indonesia",
                style = typography.labelMedium,
                color = colorScheme.onSurfaceVariant
            )
            GlideImage(
                model = "https://static.promediateknologi.id/crop/0x0:0x0/0x0/webp/photo/p2/74/2023/09/29/Gojo-Satoru-1626485619.jpg",
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) { reqBuilder ->
                reqBuilder.error("https://img.freepik.com/premium-vector/colorful-winter-cartoon-anime-error-404-page-found_150972-721.jpg")
            }
            Text(
                text = "" +
                        "Ada banyak momen seru dan berbagai kehebohan yang terjadi dalam gelaran ajang penghargaan Indonesian Dangdut Awards 2023. Mulai dari gimmick panggung dari para pengisi acara hingga saling ungkap rasa kagum dari para pembaca nominasi. Itulah yang terjadi ketika Fuji dan Lesti Kejora ketika berada satu panggung untuk pertama kalinya. Diketah..." +
                        "",
                style = typography.bodyMedium,
                color = colorScheme.onBackground
            )
            Spacer(modifier = Modifier.padding(bottom = 12.dp))
            ButtonTextIcon(
                text = {
                    Text(
                        text = "Baca Selengkapnya",
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
                // TODO: On read full of news
            }
        }
    }
}

@Preview
@Composable
fun DetailNewsScreenPreview() {
    NeoNewsTheme {
        DetailNewsScreen(
            navHostController = rememberNavController(),
            id = "Perang Palestina",
        )
    }
}