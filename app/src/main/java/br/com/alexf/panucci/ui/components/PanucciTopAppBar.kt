package br.com.alexf.panucci.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import br.com.alexf.panucci.ui.theme.PanucciTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PanucciTopAppBar(
    onProfileClick: () -> Unit = {}
) {
    CenterAlignedTopAppBar(title = {
        Text(text = "Restorante Panucci")
    }, actions = {
        IconButton(onClick = onProfileClick) {
            Icon(Icons.Outlined.AccountCircle, null)
        }
    })
}

@Preview
@Composable
private fun PanucciTopAppBarPreview() {
    PanucciTheme {
        PanucciTopAppBar()
    }
}