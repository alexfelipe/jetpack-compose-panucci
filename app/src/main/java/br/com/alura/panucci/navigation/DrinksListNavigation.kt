package br.com.alura.panucci.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import br.com.alura.panucci.sampledata.sampleProducts
import br.com.alura.panucci.ui.screens.DrinksListScreen

private val destination = AppDestination.Drinks.route

fun NavGraphBuilder.drinksListScreen(
    onNavigateToDetails: (String) -> Unit = {}
) {
    composable(destination) {
        DrinksListScreen(
            products = sampleProducts,
            onNavigateToDetails = {
                onNavigateToDetails(it.id)
            },
        )
    }
}

fun NavController.navigateSingleTopToDrinks(
    isPopUpTo: Boolean = false
) {
    navigate(destination) {
        launchSingleTop = true
        if (isPopUpTo) {
            popUpTo(destination)
        }
    }
}