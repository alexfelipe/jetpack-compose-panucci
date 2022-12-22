package br.com.alura.panucci.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.alura.panucci.dao.ProductDao
import br.com.alura.panucci.ui.uistate.ProductDetailsUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.math.MathContext
import java.math.RoundingMode
import kotlin.random.Random

class ProductDetailsViewModel(
    private val dao: ProductDao = ProductDao()
) : ViewModel() {

    private var discount: BigDecimal = BigDecimal.ZERO
    private val _uiState = MutableStateFlow<ProductDetailsUiState>(
        ProductDetailsUiState.Loading
    )
    val uiState = _uiState.asStateFlow()

    fun findProductById(id: String) {
        viewModelScope.launch {
            _uiState.update { ProductDetailsUiState.Loading }
            val timeInMillis: Long = Random.nextLong(500, 2000)
            delay(timeInMillis)
            val uiState = dao.findById(id)?.let { product ->
                val priceWithDiscount = (product.price - product.price * discount).round(
                        MathContext(
                            3,
                            RoundingMode.HALF_UP
                        )
                    )
                ProductDetailsUiState.Success(
                    product = product.copy(price = priceWithDiscount)
                )
            } ?: ProductDetailsUiState.Failure
            _uiState.update { uiState }
        }
    }

    fun applyPromoCode(code: String) {
        if (code == "PANUCCI10") {
            discount = BigDecimal("0.1")
        }
    }

}