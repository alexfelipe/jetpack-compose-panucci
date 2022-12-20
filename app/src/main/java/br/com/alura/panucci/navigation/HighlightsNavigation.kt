package br.com.alura.panucci.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import br.com.alura.panucci.sampledata.sampleProducts
import br.com.alura.panucci.ui.screens.HighlightsListScreen
import br.com.alura.panucci.ui.viewmodels.HighlightsListViewModel

const val highlightsRoute = "highlights"

fun NavGraphBuilder.highlightsScreen(navController: NavHostController) {
    composable(highlightsRoute) {
        val viewModel = viewModel<HighlightsListViewModel>()
        val uiState by viewModel.uiState.collectAsState()
        HighlightsListScreen(
            uiState = uiState,
            onNavigateToDetails = { product ->
                navController.navigateToProductDetails(product.price.toPlainString())
            },
            onNavigateToCheckout = {
                navController.navigateToCheckout()
            },
        )
    }
}

fun NavController.navigateToHighlights(
    navOptions: NavOptions? = null
) {
    navigate(highlightsRoute, navOptions)
}