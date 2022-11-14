package br.com.alexf.panucci.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import br.com.alexf.panucci.AppRoutes
import br.com.alexf.panucci.panucciBottomAppBarScreens
import br.com.alexf.panucci.ui.theme.PanucciTheme

@Composable
fun PanucciBottomAppBar(
    currentRoute: String,
    modifier: Modifier = Modifier,
    items: List<Pair<AppRoutes, ImageVector>> = emptyList(),
    onItemChange: (String) -> Unit = {}
) {
    NavigationBar(modifier) {
        items.forEach { item ->
            val label = item.first.route
            val icon = item.second
            NavigationBarItem(
                icon = { Icon(icon, contentDescription = label) },
                label = { Text(label) },
                selected = currentRoute == label,
                onClick = {
                    onItemChange(label)
                }
            )
        }
    }
}

@Preview
@Composable
fun PanucciBottomAppBarPreview() {
    PanucciTheme {
        PanucciBottomAppBar(
            currentRoute = AppRoutes.HighlightsList.route,
            items = panucciBottomAppBarScreens
        )
    }
}