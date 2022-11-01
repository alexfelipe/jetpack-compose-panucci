package br.com.alexf.panucci.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import br.com.alexf.panucci.R
import br.com.alexf.panucci.model.Product
import br.com.alexf.panucci.ui.theme.PanucciTheme
import coil.compose.AsyncImage
import java.math.BigDecimal

@Composable
fun StackedCard(
    product: Product,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Card(
        modifier
            .clip(RoundedCornerShape(12.dp)),
    ) {
        Column {
            product.image?.let { image ->
                AsyncImage(
                    image,
                    contentDescription = null,
                    Modifier
                        .fillMaxWidth()
                        .height(116.dp),
                    placeholder = painterResource(id = R.drawable.placeholder),
                    contentScale = ContentScale.Crop,
                )
            }
            Column(
                Modifier.padding(
                    horizontal = 16.dp,
                    vertical = 8.dp
                )
            ) {
                Text(text = product.name)
                Text(text = product.price.toString())
                Spacer(Modifier.height(16.dp))
                Text(text = product.description)
            }
            Spacer(Modifier.height(18.dp))
            Box(
                modifier = Modifier
                    .padding(
                        start = 16.dp,
                        end = 16.dp,
                        bottom = 24.dp
                    )
                    .background(
                        Color(0xFF6750A4),
                        shape = RoundedCornerShape(100)
                    )
                    .clip(shape = RoundedCornerShape(100))
                    .clickable {
                        onClick()
                    }
                    .padding(
                        horizontal = 24.dp,
                        vertical = 12.dp
                    )
                    .align(Alignment.End)
            ) {
                Text(
                    text = "Pedir",
                    Modifier.align(Alignment.BottomEnd),
                    color = Color.White
                )
            }
        }
    }
}

@Preview
@Composable
fun StackedCardPreview() {
    PanucciTheme {
        Surface {
            StackedCard(
                Product(
                    name = LoremIpsum(20).values.first(),
                    price = BigDecimal("9.99"),
                    description = LoremIpsum(50).values.first()
                )
            )
        }
    }
}

@Preview
@Composable
fun StackedCardWithImagePreview() {
    PanucciTheme {
        Surface {
            StackedCard(
                Product(
                    name = LoremIpsum(20).values.first(),
                    price = BigDecimal("9.99"),
                    description = LoremIpsum(50).values.first(),
                    image = ""
                )
            )
        }
    }
}