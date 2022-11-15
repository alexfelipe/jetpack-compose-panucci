package br.com.alexf.panucci

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.navOptions
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
                navController.navigateToHighlightListScreen(
                    navOptions {
                        launchSingleTop = true
                    }
                )
                navController.navigateToProductDetails(
                    productId = productId
                )
            })
        menuScreen(
            onNavigateToProductDetails = { productId ->
                navController.navigateToProductDetails(
                    productId = productId
                )
            }
        )
        drinksScreen(
            onNavigateToProductsDetails = { productId ->
                navController.navigateToProductDetails(
                    productId = productId
                )
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

