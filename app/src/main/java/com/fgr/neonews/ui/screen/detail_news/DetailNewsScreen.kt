package com.fgr.neonews.ui.screen.detail_news

import android.content.Intent
import android.net.Uri
import android.widget.Toast
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.fgr.neonews.R
import com.fgr.neonews.UiState
import com.fgr.neonews.component.button.ButtonTextIcon
import com.fgr.neonews.component.fab.FabPrimary
import com.fgr.neonews.component.navbar.NavBarSecondary
import com.fgr.neonews.data.room.table.NewsTable
import com.fgr.neonews.formatMills
import com.fgr.neonews.millsToIso
import com.fgr.neonews.ui.theme.NeoNewsTheme

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalMaterial3Api::class)
@Composable
fun DetailNewsScreen(
    navHostController: NavHostController,
    dateTime: String,
    title: String,
    source: String,
    imageUrl: String,
    description: String,
    newsUrl: String,
    detailNewsViewModel: DetailNewsViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    var isFavorite by rememberSaveable {
        mutableStateOf(false)
    }
    LaunchedEffect(detailNewsViewModel) {
        detailNewsViewModel.isFavorite(dateTime.toLong()) // Using date time (time stamp) for id
    }
    val isFavoriteState by detailNewsViewModel.isFavorite.collectAsState()
    LaunchedEffect(isFavoriteState) {
        when (val currentState = isFavoriteState) {
            UiState.Empty -> {}
            is UiState.Error -> {
                Toast.makeText(context, currentState.errorMessage, Toast.LENGTH_LONG).show()
            }

            UiState.Loading -> {}
            is UiState.Success -> {
                isFavorite = currentState.data
            }
        }
    }

    Scaffold(
        topBar = {
            NavBarSecondary(
                title = "Detail Berita"
            ) {
                navHostController.navigateUp()
            }
        },
        floatingActionButton = {
            FabPrimary(
                modifier = Modifier
                    .padding(bottom = 24.dp, end = 12.dp),
                icon = if (isFavorite) {
                    R.drawable.ic_star
                } else {
                    R.drawable.ic_start_outlined
                }
            ) {
                val currentNews = NewsTable(
                    id = dateTime.toLong(),
                    title = title,
                    source = source,
                    imageUrl = imageUrl,
                    newsUrl = newsUrl,
                    description = description,
                    publishedAt = dateTime.toLong().millsToIso(),
                    createdAt = System.currentTimeMillis(),
                )
                if (isFavorite) {
                    detailNewsViewModel.deleteFavorite(
                        id = currentNews.id,
                        callback = { success, message ->
                            if (success) detailNewsViewModel.isFavorite(currentNews.id)
                            else Toast.makeText(context, message, Toast.LENGTH_LONG).show()
                        }
                    )
                } else {
                    detailNewsViewModel.insertFavorite(
                        news = currentNews,
                        callback = { success, message ->
                            if (success) detailNewsViewModel.isFavorite(currentNews.id)
                            else Toast.makeText(context, message, Toast.LENGTH_LONG).show()
                        }
                    )
                }
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
                text = dateTime.toLong().formatMills("EEEE, dd MMMM yyyy"),
                style = typography.bodySmall,
                color = colorScheme.onSurfaceVariant
            )
            Text(
                text = title,
                style = typography.titleLarge,
                color = colorScheme.onBackground
            )
            Text(
                text = source,
                style = typography.labelMedium,
                color = colorScheme.onSurfaceVariant
            )
            GlideImage(
                model = Uri.decode(imageUrl),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) { reqBuilder ->
                reqBuilder.error("https://career.astra.co.id/static/media/image_not_available1.94c0c57d.png")
            }
            Text(
                text = description,
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
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(Uri.decode(newsUrl)))
                context.startActivity(intent)
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
            dateTime = "2023-12-11T12:14:06Z",
            title = "Netflix users poke fun at Elon Musk's reaction to 'Leave the World Behind' Tesla scen",
            source = "The Times of India",
            imageUrl = "https://img.etimg.com/thumb/msid-105906213,width-1200,height-630,imgsize-46020,overlay-economictimes/photo.jpg",
            description = "Elon Musk's criticism of a Tesla scene in Netflix's 'Leave the World Behind' stirred online banter as the post-apocalyptic thriller soared to the platform's top spot. The film, depicting a society crumbling amid technological collapse, faced mixed reviews des",
            newsUrl = "https://www.nakedcapitalism.com/2023/12/links-12-11-2023.html"
        )
    }
}