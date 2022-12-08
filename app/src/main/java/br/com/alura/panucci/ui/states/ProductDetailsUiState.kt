package br.com.alura.panucci.ui.states

import br.com.alura.panucci.model.Product

sealed interface ProductDetailsUiState {

    object Loading : ProductDetailsUiState

    object Failure: ProductDetailsUiState

    data class Success(
        val product: Product = Product()
    ) : ProductDetailsUiState

    data class Product(
        val name: String = "",
        val price: String = "",
        val description: String = "",
        val image: String? = ""
    )

}

fun Product.toProductDetailsUiStateProduct(): ProductDetailsUiState.Product {
    return ProductDetailsUiState.Product(
        name = name,
        price = price.toPlainString(),
        description = description,
        image = image
    )
}