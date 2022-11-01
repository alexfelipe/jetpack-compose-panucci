package br.com.alexf.panucci.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import br.com.alexf.panucci.ui.components.StackedCard
import br.com.alexf.panucci.ui.theme.PanucciTheme
import br.com.alexf.panucci.ui.theme.caveatFont
import java.math.BigDecimal
import kotlin.random.Random

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HighlightsListScreen(
    onHighlightClick: () -> Unit = {}
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        stickyHeader {
            Surface {
                Text(
                    text = "Destaques do dia",
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
            StackedCard(
                product = Product(
                    name = LoremIpsum(Random.nextInt(10)).values.first(),
                    price = BigDecimal("9.99"),
                    description = LoremIpsum(Random.nextInt(30)).values.first(),
                    image = "https://picsum.photos/1920/1080"
                ),
                onClick = onHighlightClick
            )
        }
    }
}

@Preview
@Composable
fun HighlightsListScreenPreview() {
    PanucciTheme {
        Surface {
            HighlightsListScreen()
        }
    }
}