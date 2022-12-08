package br.com.alura.panucci.ui.states

import br.com.alura.panucci.model.Product

data class DrinksUiState(
    val products: List<Product> = emptyList()
)