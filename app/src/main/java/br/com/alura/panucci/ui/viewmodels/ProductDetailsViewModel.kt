package br.com.alura.panucci.ui.viewmodels

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import br.com.alura.panucci.dao.ProductDao
import br.com.alura.panucci.ui.states.ProductDetailsUiState
import br.com.alura.panucci.ui.states.toProductDetailsUiStateProduct
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ProductDetailsViewModel(
    stateHandle: SavedStateHandle,
    private val dao: ProductDao = ProductDao()
) : ViewModel() {

    private val _uiState: MutableStateFlow<ProductDetailsUiState> =
        MutableStateFlow(ProductDetailsUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        stateHandle.get<String>("productId")?.let { id ->
            dao.findById(id)?.let { product ->
                _uiState.update {
                    ProductDetailsUiState.Success(
                        product.toProductDetailsUiStateProduct()
                    )
                }
            } ?: _uiState.update {
                ProductDetailsUiState.Failure
            }
        }
    }

}

class ProductDetailsViewModelFactory : AbstractSavedStateViewModelFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        return ProductDetailsViewModel(
            stateHandle = handle
        ) as T
    }

}