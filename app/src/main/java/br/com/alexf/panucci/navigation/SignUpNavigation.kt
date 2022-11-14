package br.com.alexf.panucci.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.alexf.panucci.AppRoutes
import br.com.alexf.panucci.ui.screens.SignUpScreen

fun NavGraphBuilder.signUpScreen(
    onPopBackStack: () -> Unit
) {
    composable(AppRoutes.SignUp.route) {
        SignUpScreen(onPopBackStack = onPopBackStack)
    }
}

fun NavController.navigateToSignUp() {
    navigate(AppRoutes.SignUp.route)
}