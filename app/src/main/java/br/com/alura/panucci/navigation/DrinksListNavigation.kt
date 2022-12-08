package br.com.alura.panucci.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import br.com.alura.panucci.sampledata.sampleProducts
import br.com.alura.panucci.ui.screens.DrinksListScreen
import br.com.alura.panucci.ui.viewmodels.DrinksViewModel

private val destination = AppDestination.Drinks.route

fun NavGraphBuilder.drinksListScreen(
    onNavigateToDetails: (String) -> Unit = {}
) {
    composable(destination) {
        val viewModel: DrinksViewModel = viewModel()
        val uiState by viewModel.uiState.collectAsState()
        DrinksListScreen(
            state = uiState,
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