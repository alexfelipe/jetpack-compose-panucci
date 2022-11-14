package br.com.alexf.panucci.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.alexf.panucci.AppRoutes
import br.com.alexf.panucci.sampledata.sampleProducts
import br.com.alexf.panucci.ui.screens.CheckoutScreen

fun NavGraphBuilder.checkoutScreen() {
    composable(AppRoutes.Checkout.route) {
        CheckoutScreen(
            products = sampleProducts
        )
    }
}

fun NavController.navigateToCheckoutScreen() {
    navigate(AppRoutes.Checkout.route)
}