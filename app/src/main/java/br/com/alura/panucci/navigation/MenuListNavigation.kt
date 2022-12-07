package br.com.alura.panucci.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.alura.panucci.sampledata.sampleProducts
import br.com.alura.panucci.ui.screens.MenuListScreen

private val destination = AppDestination.Menu.route

fun NavGraphBuilder.menuListScreen(
    onNavigateToDetails: (String) -> Unit
) {
    composable(destination) {
        MenuListScreen(
            products = sampleProducts,
            onNavigateToDetails = {
                onNavigateToDetails(it.id)
            }
        )
    }
}

fun NavController.navigateSingleTopToMenuList(
    isPopUpTo: Boolean = false
) {
    navigate(destination) {
        launchSingleTop = true
        if (isPopUpTo) {
            popUpTo(destination)
        }
    }
}