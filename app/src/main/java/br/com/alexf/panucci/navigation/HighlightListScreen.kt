package br.com.alexf.panucci.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import br.com.alexf.panucci.AppRoutes
import br.com.alexf.panucci.sampledata.sampleProducts
import br.com.alexf.panucci.ui.screens.HighlightsListScreen

fun NavGraphBuilder.highlightListScreen(
    onNavigateToCheckout: () -> Unit,
    onNavigateToProductDetails: (String) -> Unit,
) {
    composable(AppRoutes.HighlightsList.route) {
        HighlightsListScreen(
            products = sampleProducts,
            onAskClick = onNavigateToCheckout,
            onProductClick = onNavigateToProductDetails
        )
    }
}

fun NavController.navigateToHighlightListScreen(
    navOptions: NavOptions? = null
) {
    navigate(AppRoutes.HighlightsList.route, navOptions)
}