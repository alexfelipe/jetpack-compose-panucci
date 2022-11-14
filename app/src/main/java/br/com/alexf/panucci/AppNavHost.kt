package br.com.alexf.panucci

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import br.com.alexf.panucci.navigation.*

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: String
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        loginGraph(navController)
        highlightListScreen(
            onNavigateToCheckout = {
                navController.navigateToCheckoutScreen()
            },
            onNavigateToProductDetails = { productId ->
                navController.navigateToProductDetails(productId = productId)
            })
        menuScreen(
            onNavigateToProductDetails = { productId ->
                navController.navigateToProductDetails(productId = productId)
            }
        )
        drinksScreen(
            onNavigateToProductsDetails = { productId ->
                navController.navigateToProductDetails(productId = productId)
            }
        )
        checkoutScreen()
        productDetailsScreen(
            onNavigateToCheckout = {
                navController.navigateToCheckoutScreen()
            }
        )
    }
}

fun NavController.navigateSingleTopTo(
    route: String
) {
    navigate(route) {
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

