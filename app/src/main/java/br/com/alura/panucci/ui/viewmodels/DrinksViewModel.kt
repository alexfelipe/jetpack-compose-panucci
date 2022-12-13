package br.com.alura.panucci.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.alura.panucci.dao.ProductDao
import br.com.alura.panucci.ui.states.DrinksUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DrinksViewModel @Inject constructor(
    private val dao: ProductDao
) : ViewModel() {

    private val _uiState: MutableStateFlow<DrinksUiState> =
        MutableStateFlow(DrinksUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            dao.products.collect { products ->
                _uiState.update {
                    it.copy(products = products)
                }
            }
        }
    }

}