package br.com.alexf.panucci.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.alexf.panucci.model.Product
import br.com.alexf.panucci.sampledata.sampleProducts
import br.com.alexf.panucci.ui.components.SimpleStackedCard
import br.com.alexf.panucci.ui.theme.PanucciTheme
import br.com.alexf.panucci.ui.theme.caveatFont

@Composable
fun DrinksListScreen(
    modifier: Modifier = Modifier,
    products: List<Product> = emptyList(),
    columns: Int = 2,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(columns),
        modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item(span = {
            GridItemSpan(columns)
        }) {
            Surface {
                Text(
                    text = "Bebidas",
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
            SimpleStackedCard(
                product = p
            )
        }
    }

}

@Preview
@Composable
fun DrinksListScreenPreview() {
    PanucciTheme {
        Surface {
            DrinksListScreen(
                products = sampleProducts
            )
        }
    }
}