package br.com.alexf.panucci.model

import java.math.BigDecimal

class Product(
    val name: String,
    val price: BigDecimal,
    val description: String,
    val image: String? = null
)