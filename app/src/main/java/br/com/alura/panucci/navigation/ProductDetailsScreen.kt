package br.com.alura.panucci.navigation

import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.*
import androidx.navigation.compose.composable
import br.com.alura.panucci.ui.screens.ProductDetailsScreen
import br.com.alura.panucci.ui.viewmodels.ProductDetailsViewModel

private const val productDetailsRoute = "productDetails"

fun NavGraphBuilder.productDetailsScreen(navController: NavHostController) {
    composable(
        "$productDetailsRoute/{productId}",
        arguments = listOf(navArgument("promoCode") { defaultValue = "teste" }),
        deepLinks = listOf(navDeepLink {
            uriPattern = "$uri/$productDetailsRoute/{productId}?promoCode={promoCode}"
        })
    ) { backStackEntry ->
        val promoCode = backStackEntry.arguments?.getString("promoCode")
        Log.i("productDetails", "promocode: $promoCode")

        backStackEntry.arguments?.getString("productId")?.let { id ->
            val viewModel = viewModel<ProductDetailsViewModel>()
            val uiState by viewModel.uiState.collectAsState()
            LaunchedEffect(Unit) {
                promoCode?.let {
                    viewModel.applyPromoCode(it)
                }
                viewModel.findProductById(id)
            }
            ProductDetailsScreen(
                uiState = uiState,
                onNavigateToCheckout = {
                    navController.navigateToCheckout()
                },
                onTryLoadingProduct = {
                    viewModel.findProductById(id)
                },
                onPopBackStack = {
                    navController.navigateUp()
                }
            )
        } ?: LaunchedEffect(Unit) {
            navController.navigateUp()
        }
    }
}

fun NavController.navigateToProductDetails(id: String) {
    navigate("$productDetailsRoute/$id")
}