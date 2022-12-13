package br.com.alura.panucci.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.alura.panucci.sampledata.sampleProducts
import br.com.alura.panucci.ui.screens.MenuListScreen
import br.com.alura.panucci.ui.states.MenuUiState
import br.com.alura.panucci.ui.viewmodels.MenuViewModel

const val menuRoute = "menu"

fun NavGraphBuilder.menuListScreen(
    onNavigateToDetails: (String) -> Unit
) {
    composable(menuRoute) {
        val viewModel = viewModel<MenuViewModel>()
        val uiState by viewModel.uiState.collectAsState()
        MenuListScreen(
            state = uiState,
            onNavigateToDetails = {
                onNavigateToDetails(it.id)
            }
        )
    }
}

fun NavController.navigateSingleTopToMenuList(
    isPopUpTo: Boolean = false
) {
    navigate(menuRoute) {
        launchSingleTop = true
        if (isPopUpTo) {
            popUpTo(menuRoute)
        }
    }
}