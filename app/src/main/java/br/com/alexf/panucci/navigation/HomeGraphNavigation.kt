package br.com.alexf.panucci.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import br.com.alexf.panucci.AppRoutes
import br.com.alexf.panucci.sampledata.sampleProducts
import br.com.alexf.panucci.ui.screens.CheckoutScreen
import br.com.alexf.panucci.ui.screens.DrinksListScreen
import br.com.alexf.panucci.ui.screens.HighlightsListScreen
import br.com.alexf.panucci.ui.screens.MenuListScreen

internal fun NavGraphBuilder.mainGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = AppRoutes.HighlightsList.route,
        route = AppRoutes.Home.route
    ) {
        composable(AppRoutes.HighlightsList.route) {
            HighlightsListScreen(
                products = sampleProducts,
                onHighlightClick = {
                    navController.navigate(AppRoutes.Checkout.route)
                },
                onSaveClick = {
                })
        }
        composable(AppRoutes.Menu.route) {
            MenuListScreen(
                products = sampleProducts
            )
        }
        composable(AppRoutes.Checkout.route) {
            CheckoutScreen(
                products = sampleProducts
            )
        }
        composable(AppRoutes.Drinks.route) {
            DrinksListScreen(
                products = sampleProducts
            )
        }
    }
}