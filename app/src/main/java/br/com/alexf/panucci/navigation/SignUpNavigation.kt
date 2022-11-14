package br.com.alexf.panucci.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.alexf.panucci.AppRoutes
import br.com.alexf.panucci.ui.screens.SignUpScreen

internal fun NavGraphBuilder.signUpScreen(
    onPopBackStack: () -> Unit
) {
    composable(AppRoutes.SignUp.route) {
        SignUpScreen(onPopBackStack = onPopBackStack)
    }
}