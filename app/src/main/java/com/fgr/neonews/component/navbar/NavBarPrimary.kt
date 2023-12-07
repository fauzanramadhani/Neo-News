package com.fgr.neonews.component.navbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fgr.neonews.R
import com.fgr.neonews.component_core.NavBarBase
import com.fgr.neonews.ui.theme.NeoNewsTheme

@Composable
fun NavBarPrimary(
    modifier: Modifier = Modifier
) {
    NavBarBase(
        modifier = modifier,
        paddingValues = PaddingValues(24.dp),
        clipShape =
            RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.logoeznews),
            contentDescription = null,
            modifier = Modifier
                .size(32.dp)
        )
        Text(
            text = stringResource(id = R.string.app_name),
            style = typography.titleMedium,
            color = colorScheme.onPrimary
        )
    }
}

@Preview
@Composable
fun NavBarPrimaryPreview() {
    NeoNewsTheme {
        NavBarPrimary()
    }
}