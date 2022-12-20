package br.com.alura.panucci.ui.uistate

import br.com.alura.panucci.model.Product


sealed class ProductDetailsUiState {

    object Loading : ProductDetailsUiState()

    data class Success(val product: Product) : ProductDetailsUiState()

    object Failure : ProductDetailsUiState()

}
