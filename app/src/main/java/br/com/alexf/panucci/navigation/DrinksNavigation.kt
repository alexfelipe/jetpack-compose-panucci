package br.com.alexf.panucci.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.alexf.panucci.AppRoutes
import br.com.alexf.panucci.sampledata.sampleProducts
import br.com.alexf.panucci.ui.screens.DrinksListScreen

fun NavGraphBuilder.drinksScreen(
    onNavigateToProductsDetails: (String) -> Unit = {}
) {
    composable(AppRoutes.Drinks.route) {
        DrinksListScreen(
            products = sampleProducts,
            onNavigateToProductDetails = onNavigateToProductsDetails
        )
    }
}