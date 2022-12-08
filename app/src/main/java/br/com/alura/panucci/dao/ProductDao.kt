package br.com.alura.panucci.dao

import br.com.alura.panucci.model.Product
import br.com.alura.panucci.sampledata.sampleProducts
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProductDao {
    private val _products: MutableStateFlow<List<Product>> = MutableStateFlow(sampleProducts)
    val products = _products.asStateFlow()

    fun findById(id: String): Product? {
        return _products.value.find {
            it.id == id
        }
    }
}
