package br.com.alexf.panucci.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBalanceWallet
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material.icons.outlined.ArrowForwardIos
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.alexf.panucci.model.Product
import br.com.alexf.panucci.sampledata.sampleProducts
import br.com.alexf.panucci.ui.components.CheckoutItemCard
import br.com.alexf.panucci.ui.components.HorizontalCard
import br.com.alexf.panucci.ui.theme.PanucciTheme

@Composable
fun CheckoutScreen() {
    Box(
        Modifier.fillMaxSize()
    ) {
        LazyColumn(
            Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(
                start = 16.dp,
                top = 16.dp,
                end = 16.dp,
                bottom = 100.dp
            )
        ) {
            item {
                Text(
                    text = "Pedido",
                    fontSize = 24.sp,
                    fontWeight = FontWeight(500)
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
            items(5) {
                CheckoutItemCard(product = sampleProducts.random())
                Spacer(Modifier.height(16.dp))
            }
            item {
                Column {
                    Text(
                        text = "Pagamento",
                        fontSize = 24.sp,
                        fontWeight = FontWeight(500)
                    )
                    Row(
                        Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            Modifier.padding(vertical = 16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "VISA",
                                Modifier
                                    .background(
                                        MaterialTheme.colorScheme.primaryContainer,
                                        shape = RoundedCornerShape(4.dp)
                                    )
                                    .padding(4.dp)
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Column {
                                Text(text = "VISA Classic")
                                Text(text = "****-0976")
                            }
                        }
                        Icon(
                            Icons.Outlined.ArrowForwardIos,
                            contentDescription = null
                        )
                    }
                }
            }
            item {
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = "Confirmar",
                        fontSize = 24.sp,
                        fontWeight = FontWeight(500)
                    )
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "Pedido")
                            Text(text = "9.0")
                        }
                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "Serviço (10%)")
                            Text(text = "9.0")
                        }
                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "Total")
                            Text(text = "9.0")
                        }
                    }
                }
            }
        }
        Box(
            Modifier
                .padding(
                    16.dp
                )
                .heightIn(50.dp)
                .fillMaxWidth()
                .background(
                    Color(0xFF6750A4),
                    shape = RoundedCornerShape(100.dp)
                )
                .align(Alignment.BottomCenter),
        ) {
            Row(
                Modifier
                    .align(Alignment.Center)
                    .padding(vertical = 14.dp)
            ) {
                Icon(
                    Icons.Outlined.AccountBalanceWallet,
                    contentDescription = null,
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Pedir",
                    color = Color.White
                )
            }
        }
    }
}



@Preview(heightDp = 400)
@Preview
@Composable
fun CheckoutScreenPreview() {
    PanucciTheme {
        Surface {
            CheckoutScreen()
        }
    }
}