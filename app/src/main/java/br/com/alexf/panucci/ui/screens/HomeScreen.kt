package br.com.alexf.panucci.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import br.com.alexf.panucci.model.Product
import br.com.alexf.panucci.ui.components.StackedCard
import br.com.alexf.panucci.ui.theme.PanucciTheme
import java.math.BigDecimal
import kotlin.random.Random

@Composable
fun HomeScreen() {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(10) {
            StackedCard(
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
fun HomeScreenPreview() {
    PanucciTheme {
        Surface {
            HomeScreen()
        }
    }
}