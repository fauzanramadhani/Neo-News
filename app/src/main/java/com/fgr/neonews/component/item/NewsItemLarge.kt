package com.fgr.neonews.component.item

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.fgr.neonews.truncate

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun NewsItemLarge(
    modifier: Modifier = Modifier,
    imageUrl: String = "",
    title: String = "",
    onClick: () -> Unit = {},
) {
    Card(
        shape = RoundedCornerShape(4.dp),
        modifier = modifier
            .fillMaxWidth()
            .height(112.dp)
            .clickable(onClick = onClick)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(end = 12.dp)
        ) {
            GlideImage(
                model = imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth(0.3f)
                    .fillMaxHeight()
            ) {
                it.error("https://career.astra.co.id/static/media/image_not_available1.94c0c57d.png")
            }
            Text(
                text = title.truncate(224),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(vertical = 6.dp)
            )
        }
    }
}