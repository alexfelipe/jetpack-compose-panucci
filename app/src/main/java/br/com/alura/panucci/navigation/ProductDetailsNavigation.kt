package br.com.alura.panucci.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.alura.panucci.sampledata.sampleProducts
import br.com.alura.panucci.ui.screens.ProductDetailsScreen

private val destination = AppDestination.ProductDetails.route

fun NavGraphBuilder.productDetailsScreen(
    onNavigateToCheckout: () -> Unit = {},
    onPopBackStack: () -> Unit = {}
) {
    composable(
        "${destination}/{productId}",
    ) { backStackEntry ->
        val id = backStackEntry.arguments?.getString("productId")
        sampleProducts.find {
            it.id == id
        }?.let { product ->
            ProductDetailsScreen(
                product = product,
                onNavigateToCheckout = onNavigateToCheckout,
            )
        } ?: onPopBackStack()
    }
}

fun NavController.navigateToProductDetails(id: String) {
    navigate("$destination/$id")
}