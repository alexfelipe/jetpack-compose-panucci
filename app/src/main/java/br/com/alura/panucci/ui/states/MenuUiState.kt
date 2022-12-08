package br.com.alura.panucci.ui.states

import br.com.alura.panucci.model.Product

data class MenuUiState(
    val products: List<Product> = emptyList()
)