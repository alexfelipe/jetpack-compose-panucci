package br.com.alura.panucci.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.alura.panucci.ui.screens.HighlightsListScreen
import br.com.alura.panucci.ui.viewmodels.HighlightsViewModel

const val highlightsRoute = "highlights"

fun NavGraphBuilder.highlightsListScreen(
    onNavigateToDetails: (String) -> Unit = {},
    onNavigateToCheckout: () -> Unit = {}
) {
    composable(
        highlightsRoute
    ) {
        val viewModel = hiltViewModel<HighlightsViewModel>()
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
    navigate(highlightsRoute) {
        launchSingleTop = true
        if (isPopUpto) {
            popUpTo(highlightsRoute)
        }
    }
}