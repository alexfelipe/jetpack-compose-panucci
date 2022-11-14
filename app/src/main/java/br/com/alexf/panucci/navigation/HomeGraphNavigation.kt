package br.com.alexf.panucci.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import br.com.alexf.panucci.AppRoutes

fun NavGraphBuilder.mainGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = AppRoutes.HighlightsList.route,
        route = AppRoutes.Home.route
    ) {
        highlightListScreen(
            onNavigateToCheckout = {
                navController.navigateToCheckoutScreen()
            })
        menuScreen()
        drinksScreen()
    }
}