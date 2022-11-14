package br.com.alexf.panucci.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.alexf.panucci.AppRoutes
import br.com.alexf.panucci.sampledata.sampleProducts
import br.com.alexf.panucci.ui.screens.MenuListScreen

fun NavGraphBuilder.menuScreen(
    onNavigateToProductDetails: (String) -> Unit = {}
) {
    composable(AppRoutes.Menu.route) {
        MenuListScreen(
            products = sampleProducts,
            onProductClick = onNavigateToProductDetails
        )
    }
}