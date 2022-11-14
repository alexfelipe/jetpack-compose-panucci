package br.com.alexf.panucci.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.alexf.panucci.model.Product
import br.com.alexf.panucci.sampledata.sampleProducts
import br.com.alexf.panucci.ui.theme.PanucciTheme
import coil.compose.AsyncImage

@Composable
fun SimpleStackedCard(
    product: Product,
    modifier: Modifier = Modifier
) {
    Card(
        modifier
            .width(158.dp)
            .height(200.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        AsyncImage(
            model = product.image,
            contentDescription = null,
        )
        Column(Modifier.padding(16.dp)) {
            Text(
                text = product.name,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Text(text = product.price.toPlainString())
        }
    }
}

@Preview
@Composable
fun SimpleStackedCardPreview() {
    PanucciTheme {
        SimpleStackedCard(product = sampleProducts.random())
    }
}