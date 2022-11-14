package br.com.alexf.panucci.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.alexf.panucci.R
import br.com.alexf.panucci.model.Product
import br.com.alexf.panucci.sampledata.sampleProducts
import br.com.alexf.panucci.ui.theme.PanucciTheme
import coil.compose.AsyncImage

@Composable
fun ProductDetailsScreen(
    product: Product,
    modifier: Modifier = Modifier,
    onAskClick: () -> Unit = {}
) {
    Column(
        modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        product.image?.let {
            AsyncImage(
                model = product.image,
                contentDescription = null,
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth(),
                placeholder = painterResource(id = R.drawable.placeholder),
                contentScale = ContentScale.Crop
            )
        }
        Column(
            Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(product.name, fontSize = 24.sp)
            Text(product.price.toPlainString(), fontSize = 18.sp)
            Text(product.description)
            Button(
                onClick = onAskClick,
                Modifier
                    .fillMaxWidth(),
                colors = ButtonDefaults
                    .buttonColors(containerColor = Color(0xFF6750A4))
            ) {
                Text(text = "Pedir")
            }
        }
    }
}

@Preview
@Composable
fun ProductDetailsScreenPreview() {
    PanucciTheme {
        Surface {
            ProductDetailsScreen(
                product = sampleProducts.random(),
            )
        }
    }
}