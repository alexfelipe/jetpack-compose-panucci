package br.com.alura.panucci.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import br.com.alura.panucci.navigation.*

@Composable
fun PanucciNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = AppDestination.Highlight.route
    ) {
        // TODO implementar o view model para cada tela
        // TODO utilizar o hilt para carregar o view model
        // TODO usar o nested graph para a tela que contém as abas
        // TODO modificar a tela de bebidas e detalhes do produto para acessar via deep link

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

