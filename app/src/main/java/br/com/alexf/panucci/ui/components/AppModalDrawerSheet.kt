package br.com.alexf.panucci.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FactCheck
import androidx.compose.material.icons.outlined.ReceiptLong
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.alexf.panucci.ui.theme.PanucciTheme

data class NavigationDrawerItemModel(
    val label: String,
    val icon: ImageVector,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppModalDrawerSheet(
    modifier: Modifier = Modifier,
    onItemClick: () -> Unit = {}
) {
    ModalDrawerSheet(modifier) {
        Spacer(Modifier.height(12.dp))
        NavigationDrawerItem(
            label = { Text("Mesa 03") },
            selected = false,
            onClick = {

            },
            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
        )
        NavigationDrawerItem(
            label = { Text("Conta") },
            selected = false,
            onClick = {

            },
            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
        )
        var currentLabel by remember {
            mutableStateOf("Pedido atual")
        }
        val items = listOf(
            NavigationDrawerItemModel(
                label = "Pedido atual",
                icon = Icons.Outlined.ReceiptLong
            ), NavigationDrawerItemModel(
                label = "Todos os pedidos",
                icon = Icons.Outlined.FactCheck
            )
        )
        items.forEach { item ->
            NavigationDrawerItem(
                icon = { Icon(item.icon, contentDescription = null) },
                label = {
                    Text(text = item.label)
                },
                selected = item.label == currentLabel,
                onClick = {
                    currentLabel = item.label
                },
                modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
            )
        }
    }
}

@Preview
@Composable
fun AppModalDrawerSheetPreview() {
    PanucciTheme {
        Surface {
            AppModalDrawerSheet()
        }
    }
}