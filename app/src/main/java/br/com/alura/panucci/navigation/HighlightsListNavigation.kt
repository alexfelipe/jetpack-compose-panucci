package br.com.alura.panucci.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.alura.panucci.sampledata.sampleProducts
import br.com.alura.panucci.ui.screens.HighlightsListScreen
import br.com.alura.panucci.ui.viewmodels.HighlightsViewModel

private val destination = AppDestination.Highlight.route

fun NavGraphBuilder.highlightsListScreen(
    onNavigateToDetails: (String) -> Unit = {},
    onNavigateToCheckout: () -> Unit = {}
) {
    composable(destination) {
        val viewModel = viewModel<HighlightsViewModel>()
        val uiState by viewModel.uiState.collectAsState()
        HighlightsListScreen(
            state = uiState,
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