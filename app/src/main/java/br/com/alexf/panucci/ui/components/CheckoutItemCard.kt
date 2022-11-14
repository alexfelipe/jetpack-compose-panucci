package br.com.alexf.panucci.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.alexf.panucci.R
import br.com.alexf.panucci.model.Product
import br.com.alexf.panucci.sampledata.sampleProducts
import br.com.alexf.panucci.ui.theme.PanucciTheme
import coil.compose.AsyncImage

@Composable
fun CheckoutItemCard(
    product: Product,
    modifier: Modifier = Modifier
) {
    Card(
        modifier,
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .height(80.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Row {
                AsyncImage(
                    model = product.image,
                    contentDescription = null,
                    Modifier.width(80.dp),
                    placeholder = painterResource(
                        id = R.drawable.placeholder
                    ),
                    contentScale = ContentScale.Crop
                )
                Column(
                    Modifier
                        .padding(8.dp)
                        .fillMaxWidth(0.9f)
                ) {
                    Text(
                        text = product.name,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = product.price.toPlainString())
                }
            }
            Column(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                var quantity by remember {
                    mutableStateOf(1)
                }
                Box(
                    Modifier
                        .size(20.dp)
                        .background(
                            Color(0xFFCCB6DB),
                            shape = CircleShape
                        )
                        .clip(CircleShape)
                        .clickable { quantity++ }
                ) {
                    Icon(
                        Icons.Filled.ArrowDropUp,
                        contentDescription = null
                    )
                }
                Text(text = quantity.toString())
                Box(
                    Modifier
                        .size(20.dp)
                        .background(
                            Color(0xFFCCB6DB),
                            shape = CircleShape
                        )
                        .clip(CircleShape)
                        .clickable {
                            if (quantity > 1) {
                                quantity--
                            }
                        }
                ) {
                    Icon(
                        Icons.Filled.ArrowDropDown,
                        contentDescription = null
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun CheckoutItemCardPreview() {
    PanucciTheme {
        Surface {
            CheckoutItemCard(product = sampleProducts.random())
        }
    }
}