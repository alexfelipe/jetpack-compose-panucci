package br.com.alexf.panucci.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.alexf.panucci.AppRoutes
import br.com.alexf.panucci.sampledata.sampleProducts
import br.com.alexf.panucci.ui.screens.HighlightsListScreen

fun NavGraphBuilder.highlightListScreen(
    onNavigateToCheckout: () -> Unit
) {
    composable(AppRoutes.HighlightsList.route) {
        HighlightsListScreen(
            products = sampleProducts,
            onHighlightClick = onNavigateToCheckout
        )
    }
}