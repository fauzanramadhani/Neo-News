package com.fgr.neonews.component.item

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
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
    modifier: Modifier = Modifier,
    imageUrl: String = "",
    title: String = "",
    onClick: () -> Unit = {},
) {
    Card(
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier
            .width(168.dp)
            .height(173.dp)
            .clickable(onClick = onClick)
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(colorScheme.surfaceVariant)
        ) {
            GlideImage(
                model = imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.6f)
            ) {
                it.error("https://career.astra.co.id/static/media/image_not_available1.94c0c57d.png")
            }
            Text(
                text = title.truncate(50),
                style = typography.labelSmall,
                color = colorScheme.onPrimaryContainer,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
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