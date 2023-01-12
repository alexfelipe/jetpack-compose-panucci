package br.com.alura.panucci.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

internal const val uri = "alura://panucci.com.br"

@Composable
fun PanucciNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = homeGraphRoute
    ) {
        homeGraph(
            onNavigateToCheckout = {
                navController.navigateToCheckout()
            },
            onNavigateToProductDetails = { product ->
                navController.navigateToProductDetails(product.id)
            },
        )
        productDetailsScreen(
            onNavigateToCheckout = {
                navController.navigateToCheckout()
            },
            onPopBackStack = {
                navController.navigateUp()
            },
        )
        checkoutScreen(
            onPopBackStack = {
                navController.previousBackStackEntry?.savedStateHandle?.set(
                    "successed_order",
                    "O pedido foi feito com sucesso"
                )
                navController.navigateUp()
            },
        )
    }

}
