package br.com.alexf.panucci.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.alexf.panucci.R
import br.com.alexf.panucci.model.Product
import br.com.alexf.panucci.sampledata.sampleProducts
import br.com.alexf.panucci.ui.theme.PanucciTheme
import coil.compose.AsyncImage

@Composable
fun HorizontalCard(
    product: Product,
    modifier: Modifier = Modifier
) {
    Card(
        modifier
            .fillMaxWidth()
            .height(80.dp)
    ) {
        Row {
            Column(
                Modifier
                    .padding(16.dp)
                    .fillMaxWidth(0.75f)
            ) {
                Text(
                    text = product.name,
                    Modifier.fillMaxWidth(),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500)
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = product.price.toPlainString(),
                    Modifier.fillMaxWidth(),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight(400)
                )
            }
            AsyncImage(
                model = product.image,
                contentDescription = null,
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                placeholder = painterResource(id = R.drawable.placeholder),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Preview
@Composable
fun HorizontalCardPreview() {
    PanucciTheme {
        HorizontalCard(product = sampleProducts.random())
    }
}