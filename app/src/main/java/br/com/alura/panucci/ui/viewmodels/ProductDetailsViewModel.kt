package br.com.alura.panucci.ui.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import br.com.alura.panucci.dao.ProductDao
import br.com.alura.panucci.ui.states.ProductDetailsUiState
import br.com.alura.panucci.ui.states.toProductDetailsUiStateProduct
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
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