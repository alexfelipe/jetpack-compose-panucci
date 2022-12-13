package br.com.alura.panucci.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import br.com.alura.panucci.ui.screens.ProductDetailsScreen
import br.com.alura.panucci.ui.viewmodels.ProductDetailsViewModel
import br.com.alura.panucci.ui.viewmodels.ProductDetailsViewModelFactory

const val productDetailsRoute = "productDetails"

fun NavGraphBuilder.productDetailsScreen(
    onNavigateToCheckout: () -> Unit = {},
    onPopBackStack: () -> Unit = {}
) {
    composable(
        "${productDetailsRoute}/{productId}",
        deepLinks = listOf(navDeepLink { uriPattern = "$uri/$productDetailsRoute/{productId}" })
    ) {
        val viewModel =
            viewModel<ProductDetailsViewModel>(factory = ProductDetailsViewModelFactory())
        val uiState by viewModel.uiState.collectAsState()
        ProductDetailsScreen(
            state = uiState,
            onNavigateToCheckout = onNavigateToCheckout,
            onFailureProductLoading = onPopBackStack
        )
    }
}

fun NavController.navigateToProductDetails(id: String) {
    navigate("$productDetailsRoute/$id")
}
