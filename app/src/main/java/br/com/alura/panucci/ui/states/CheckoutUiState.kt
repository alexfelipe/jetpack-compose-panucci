package br.com.alura.panucci.ui.states

import br.com.alura.panucci.model.Product

data class CheckoutUiState(
    val products: List<Product> = emptyList()
)