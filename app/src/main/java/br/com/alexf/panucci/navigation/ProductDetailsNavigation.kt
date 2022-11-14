package br.com.alexf.panucci.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import br.com.alexf.panucci.AppRoutes
import br.com.alexf.panucci.sampledata.sampleProducts
import br.com.alexf.panucci.ui.screens.ProductDetailsScreen

private const val productId = "productId"

fun NavGraphBuilder.productDetailsScreen(
    onNavigateToCheckout: () -> Unit = {}
) {
    composable(
        "${AppRoutes.ProductDetails.route}/{$productId}",
        arguments = listOf(navArgument(productId) { type = NavType.StringType })
    ) { backStackEntry ->
        backStackEntry.arguments?.getString(productId)?.let { id ->
            sampleProducts.find { it.id == id }?.let { product ->
                ProductDetailsScreen(
                    product = product,
                    onAskClick = onNavigateToCheckout
                )
            }
        }
    }
}

fun NavController.navigateToProductDetails(productId: String) {
    navigate("${AppRoutes.ProductDetails.route}/$productId")
}