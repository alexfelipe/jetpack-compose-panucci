package br.com.alexf.panucci.sampledata

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import br.com.alexf.panucci.model.Product
import java.math.BigDecimal
import kotlin.random.Random

val sampleProducts = listOf(
    Product(
        name = LoremIpsum(Random.nextInt(10)).values.first(),
        price = BigDecimal("9.99"),
        description = LoremIpsum(Random.nextInt(30)).values.first(),
        image = "https://picsum.photos/1920/1080"
    )
)