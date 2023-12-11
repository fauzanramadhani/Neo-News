package com.fgr.neonews.component.fab

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource

@Composable
fun FabPrimary(
    icon: Int,
    onClick: () -> Unit,
) {
    FloatingActionButton(
        onClick = onClick,
        containerColor = colorScheme.surface,
        contentColor = colorScheme.surfaceTint,
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
        )
    }
}