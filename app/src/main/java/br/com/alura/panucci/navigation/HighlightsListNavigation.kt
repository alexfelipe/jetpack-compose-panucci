package br.com.alura.panucci.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import br.com.alura.panucci.sampledata.sampleProducts
import br.com.alura.panucci.ui.screens.HighlightsListScreen

private val destination = AppDestination.Highlight.route

fun NavGraphBuilder.highlightsListScreen(
    onNavigateToDetails: (String) -> Unit = {},
    onNavigateToCheckout: () -> Unit = {}
) {
    composable(destination) {
        HighlightsListScreen(
            products = sampleProducts,
            onNavigateToDetails = { product ->
                onNavigateToDetails(product.id)
            },
            onNavigateToCheckout = onNavigateToCheckout,
        )
    }
}

fun NavController.navigateSingleTopToHighlightsList(
    isPopUpto: Boolean = false
) {
    navigate(destination) {
        launchSingleTop = true
        if (isPopUpto) {
            popUpTo(destination)
        }
    }
}