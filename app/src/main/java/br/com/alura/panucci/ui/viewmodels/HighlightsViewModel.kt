package br.com.alura.panucci.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.alura.panucci.dao.ProductDao
import br.com.alura.panucci.ui.states.HighlightsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HighlightsViewModel @Inject constructor(
    private val dao: ProductDao
) : ViewModel() {

    private val _products: MutableStateFlow<HighlightsUiState> =
        MutableStateFlow(HighlightsUiState())
    val uiState = _products.asStateFlow()

    init {
        viewModelScope.launch {
            dao.products.collect { products ->
                _products.update {
                    it.copy(products = products)
                }
            }
        }
    }

}