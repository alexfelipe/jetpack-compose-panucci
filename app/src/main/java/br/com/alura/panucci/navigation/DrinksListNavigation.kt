package br.com.alura.panucci.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import br.com.alura.panucci.ui.screens.DrinksListScreen
import br.com.alura.panucci.ui.viewmodels.DrinksViewModel

const val drinksRoute = "drinks"

fun NavGraphBuilder.drinksListScreen(
    onNavigateToDetails: (String) -> Unit = {}
) {
    composable(
        drinksRoute,
        deepLinks = listOf(navDeepLink { uriPattern = "$uri/$drinksRoute" })
    ) {
        val viewModel: DrinksViewModel = hiltViewModel()
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
    navigate(drinksRoute) {
        launchSingleTop = true
        if (isPopUpTo) {
            popUpTo(drinksRoute)
        }
    }
}