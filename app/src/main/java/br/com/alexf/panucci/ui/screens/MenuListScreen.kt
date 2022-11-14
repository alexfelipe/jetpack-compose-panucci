package br.com.alexf.panucci.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.alexf.panucci.model.Product
import br.com.alexf.panucci.sampledata.sampleProducts
import br.com.alexf.panucci.ui.components.HorizontalCard
import br.com.alexf.panucci.ui.theme.PanucciTheme
import br.com.alexf.panucci.ui.theme.caveatFont
import java.math.BigDecimal

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MenuListScreen(
    modifier: Modifier = Modifier,
    products: List<Product> = emptyList()
) {
    LazyColumn(
        modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        stickyHeader {
            Surface {
                Text(
                    text = "Menu",
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    fontFamily = caveatFont,
                    fontSize = 32.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
        items(products) { p ->
            HorizontalCard(
                product = p
            )
        }
    }
}

@Preview
@Composable
fun MenuListScreenPreview() {
    PanucciTheme {
        Surface {
            MenuListScreen(
                products = sampleProducts
            )
        }
    }
}