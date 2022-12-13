package br.com.alura.panucci.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.alura.panucci.ui.screens.CheckoutScreen
import br.com.alura.panucci.ui.viewmodels.CheckoutViewModel

const val checkoutRoute = "checkout"

fun NavGraphBuilder.checkoutScreen(
    onPopBackStack: () -> Unit = {}
) {
    composable(checkoutRoute) {
        val viewModel = viewModel<CheckoutViewModel>()
        val uiState by viewModel.uiState.collectAsState()
        CheckoutScreen(
            state = uiState,
            onPopBackStack = onPopBackStack,
        )
    }
}

fun NavController.navigateToCheckout() {
    navigate(checkoutRoute)
}