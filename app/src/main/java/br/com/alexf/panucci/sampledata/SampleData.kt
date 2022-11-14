package br.com.alexf.panucci.sampledata

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import br.com.alexf.panucci.model.Product
import java.math.BigDecimal
import kotlin.random.Random

val loremName = LoremIpsum(Random.nextInt(10)).values.first()
val loremDesc = LoremIpsum(Random.nextInt(30)).values.first()

val sampleProducts = listOf(
    Product(
        name = loremName,
        price = BigDecimal("9.99"),
        description = loremDesc,
        image = "https://picsum.photos/1920/1080"
    ),Product(
        name = loremName,
        price = BigDecimal("9.99"),
        description = loremDesc,
        image = "https://picsum.photos/1920/1080"
    ),Product(
        name = loremName,
        price = BigDecimal("9.99"),
        description = loremDesc,
        image = "https://picsum.photos/1920/1080"
    ),
    Product(
        name = loremName,
        price = BigDecimal("9.99"),
        description = loremDesc,
        image = "https://picsum.photos/1920/1080"
    ),
)