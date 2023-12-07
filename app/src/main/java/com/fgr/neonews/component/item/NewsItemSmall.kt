package com.fgr.neonews.component.item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.fgr.neonews.truncate
import com.fgr.neonews.ui.theme.NeoNewsTheme

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun NewsItemSmall(
    imageUrl: String = "",
    title: String = "",
) {
    Card(
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier
            .width(168.dp)
            .height(173.dp)
    ) {
        Column() {
            GlideImage(
                model = imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.6f)
                    .background(colorScheme.primaryContainer)
            ) {
                it.error("https://img.freepik.com/premium-vector/colorful-winter-cartoon-anime-error-404-page-found_150972-721.jpg")
            }
            Text(
                text = title.truncate(58),
                style = typography.labelSmall,
                color = colorScheme.onPrimaryContainer,
                modifier = Modifier
                    .padding(vertical = 6.dp, horizontal = 12.dp)
            )
        }
    }
}

@Preview
@Composable
fun NewsItemSmallPreview() {
    NeoNewsTheme {
        NewsItemSmall(
            title = "Kali Pertama Berdiri Satu Panggung, Fuji Ungkap Rasa Kagum Terhadap Lesti Kejora"
        )
    }
}