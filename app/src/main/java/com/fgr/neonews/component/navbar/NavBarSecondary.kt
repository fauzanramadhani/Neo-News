package com.fgr.neonews.component.navbar

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fgr.neonews.component_core.NavBarBase
import com.fgr.neonews.ui.theme.NeoNewsTheme

@Composable
fun NavBarSecondary(
    modifier: Modifier = Modifier,
    title: String = "",
    onBackPressed: () -> Unit = {},
) {
    NavBarBase(
        modifier = modifier,
        paddingValues = PaddingValues(start = 6.dp, end = 12.dp, top = 6.dp, bottom = 6.dp)
    ) {
        IconButton(onClick = onBackPressed) {
            Icon(
                imageVector = Icons.Rounded.ArrowBack,
                contentDescription = null,
                tint = colorScheme.onPrimary
            )
        }
        Text(
            text = title,
            style = typography.titleMedium,
            color = colorScheme.onPrimary
        )
    }
}

@Preview
@Composable
fun NavBarSecondaryPreview() {
    NeoNewsTheme {
        NavBarSecondary(
            title = "Latest News"
        )
    }
}