package br.com.alexf.panucci.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
import br.com.alexf.panucci.ui.components.SimpleStackedCard
import br.com.alexf.panucci.ui.theme.caveatFont
import java.math.BigDecimal
import kotlin.random.Random

@Composable
fun DrinksListScreen() {
    LazyVerticalGrid(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        item(span = {
            GridItemSpan(2)
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
        items(10) {
            SimpleStackedCard(
                product = Product(
                    name = LoremIpsum(Random.nextInt(10)).values.first(),
                    price = BigDecimal("9.99"),
                    description = LoremIpsum(Random.nextInt(30)).values.first(),
                    image = "https://picsum.photos/1920/1080"
                )
            )
        }
    }

}

@Preview
@Composable
fun DrinksListScreenPreview() {
    DrinksListScreen()
}