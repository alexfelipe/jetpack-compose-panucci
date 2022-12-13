package br.com.alura.panucci.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import br.com.alura.panucci.navigation.*

const val uri = "https://www.panuccialura.com.br"

@Composable
fun PanucciNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = HomeRouteGraphRoute
    ) {
        homeGraph(navController)
        productDetailsScreen(
            onNavigateToCheckout = {
                navController.navigateToCheckout()
            },
            onPopBackStack = {
                navController.navigateUp()
            }
        )
        checkoutScreen(
            onPopBackStack = {
                navController.navigateUp()
            }
        )
    }
}

