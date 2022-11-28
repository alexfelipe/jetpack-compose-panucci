package br.com.alura.panucci.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.com.alura.panucci.sampledata.sampleProducts
import br.com.alura.panucci.ui.screens.*

@Composable
fun PanucciNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = AppDestination.Highlight.route
    ) {
        composable(AppDestination.Highlight.route) {
            HighlightsListScreen(
                products = sampleProducts,
                onNavigateProductDetails = {
                    navController.navigate("${AppDestination.ProductDetails.route}/${it.id}")
                }
            ) {
                navController.navigate(AppDestination.Checkout.route)
            }
        }
        composable(AppDestination.Menu.route) {
            MenuListScreen(
                products = sampleProducts,
                onNavigateToProductDetails = {
                    navController.navigate(AppDestination.ProductDetails.route)
                })
        }
        composable(AppDestination.Drinks.route) {
            DrinksListScreen(
                products = sampleProducts,
                onNavigateProductDetails = {
                    navController.navigate(AppDestination.ProductDetails.route)
                }
            )
        }
        composable(
            "${AppDestination.ProductDetails.route}/{productId}",
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")
            sampleProducts.find {
                it.id == productId
            }?.let { product ->
                ProductDetailsScreen(product = product)
            } ?: LaunchedEffect(Unit) {
                navController.navigateUp()
            }
        }
        composable(AppDestination.Checkout.route) {
            CheckoutScreen(
                products = sampleProducts,
                onOrderClick = {
                    navController.popUpToStartDestination()
                })
        }
    }
}


fun NavController.popUpToStartDestination() {
    val startDestination =
        graph.findStartDestination()
    startDestination.route?.let { route ->
        navigate(route) {
            popUpTo(startDestination.id) {
                inclusive = true
            }
        }
    }
}

fun NavController.navigateToBottomAppBarItem(
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