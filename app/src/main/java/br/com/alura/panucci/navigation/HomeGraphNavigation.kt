package br.com.alura.panucci.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation

const val HomeRouteGraphRoute = "home"

fun NavGraphBuilder.homeGraph(navController: NavController) {
    navigation(
        startDestination = highlightsRoute,
        route = HomeRouteGraphRoute,
    ) {
        highlightsListScreen(
            onNavigateToDetails = { id ->
                navController.navigateToProductDetails(id)
            },
            onNavigateToCheckout = {
                navController.navigateToCheckout()
            }
        )
        menuListScreen(
            onNavigateToDetails = { id ->
                navController.navigateToProductDetails(id)
            }
        )
        drinksListScreen(
            onNavigateToDetails = { id ->
                navController.navigateToProductDetails(id)
            }
        )
    }
}