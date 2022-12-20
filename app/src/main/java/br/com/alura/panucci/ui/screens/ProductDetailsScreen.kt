package br.com.alura.panucci.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.alura.panucci.R
import br.com.alura.panucci.sampledata.sampleProducts
import br.com.alura.panucci.ui.theme.PanucciTheme
import br.com.alura.panucci.ui.uistate.ProductDetailsUiState
import coil.compose.AsyncImage

@Composable
fun ProductDetailsScreen(
    uiState: ProductDetailsUiState,
    modifier: Modifier = Modifier,
    onNavigateToCheckout: () -> Unit = {},
    onTryLoadingProduct: () -> Unit = {},
    onPopBackStack: () -> Unit = {}
) {
    when (uiState) {
        ProductDetailsUiState.Failure -> {
            Box(
                Modifier.fillMaxSize(),
            ) {
                Column(
                    Modifier.align(Center),
                    horizontalAlignment = CenterHorizontally
                ) {
                    Icon(
                        Icons.Filled.Warning,
                        contentDescription = null,
                        Modifier.size(48.dp)
                    )
                    Text(
                        text = "Falha ao carregar o produto",
                        Modifier.padding(32.dp),
                        fontSize = 20.sp
                    )
                    Button(onClick = onTryLoadingProduct) {
                        Text(text = "Carregar novamente")
                    }
                    Spacer(modifier = Modifier.size(8.dp))
                    TextButton(onClick = onPopBackStack) {
                        Text(text = "Voltar")
                    }
                }
            }
        }
        ProductDetailsUiState.Loading -> {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(Modifier.align(Alignment.Center))
            }
        }
        is ProductDetailsUiState.Success -> {
            Column(
                modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                val product = uiState.product
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
                        onClick = { onNavigateToCheckout() },
                        Modifier
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                    ) {
                        Text(text = "Pedir")
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun ProductDetailsScreenLoadingStatePreview() {
    PanucciTheme {
        Surface {
            ProductDetailsScreen(
                uiState = ProductDetailsUiState.Loading
            )
        }
    }
}

@Preview
@Composable
fun ProductDetailsScreenSuccessStatePreview() {
    PanucciTheme {
        Surface {
            ProductDetailsScreen(
                uiState = ProductDetailsUiState.Success(sampleProducts.random())
            )
        }
    }
}

@Preview
@Composable
fun ProductDetailsScreenFailureStatePreview() {
    PanucciTheme {
        Surface {
            ProductDetailsScreen(
                uiState = ProductDetailsUiState.Failure
            )
        }
    }
}