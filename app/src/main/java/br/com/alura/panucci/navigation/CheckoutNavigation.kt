package br.com.alura.panucci.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.alura.panucci.sampledata.sampleProducts
import br.com.alura.panucci.ui.screens.CheckoutScreen

private val destination = AppDestination.Checkout.route

fun NavGraphBuilder.checkoutScreen(
    onPopBackStack: () -> Unit = {}
) {
    composable(destination) {
        CheckoutScreen(
            products = sampleProducts,
            onPopBackStack = onPopBackStack,
        )
    }
}

fun NavController.navigateToCheckout() {
    navigate(destination)
}