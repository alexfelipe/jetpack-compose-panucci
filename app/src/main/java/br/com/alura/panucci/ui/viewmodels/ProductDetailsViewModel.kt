package br.com.alura.panucci.ui.viewmodels

import android.util.Log
import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import br.com.alura.panucci.dao.ProductDao
import br.com.alura.panucci.navigation.productIdArgument
import br.com.alura.panucci.ui.uistate.ProductDetailsUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.random.Random

class ProductDetailsViewModel(
    private val dao: ProductDao = ProductDao(),
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _uiState = MutableStateFlow<ProductDetailsUiState>(
        ProductDetailsUiState.Loading
    )
    val uiState = _uiState.asStateFlow()

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val savedStateHandle = createSavedStateHandle()
                ProductDetailsViewModel(
                    savedStateHandle = savedStateHandle
                )
            }
        }
    }

    init {
        savedStateHandle
            .getStateFlow<String?>(productIdArgument, null)
            .value?.let {
                Log.i("ViewModel", "init: $it")
                findProductById(it)
            }
    }

    fun findProductById(id: String) {
        _uiState.update { ProductDetailsUiState.Loading }
        viewModelScope.launch {
            val timemillis = Random.nextLong(500, 2000)
            delay(timemillis)
            val dataState = dao.findById(id)?.let { product ->
                ProductDetailsUiState.Success(product = product)
            } ?: ProductDetailsUiState.Failure
            _uiState.update { dataState }
        }
    }

}