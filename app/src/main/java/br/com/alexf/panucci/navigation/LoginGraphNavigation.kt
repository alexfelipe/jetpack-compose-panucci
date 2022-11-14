package br.com.alexf.panucci.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import br.com.alexf.panucci.AppRoutes

fun NavGraphBuilder.loginGraph(
    navController: NavController
) {
    navigation(
        startDestination = AppRoutes.SignIn.route,
        route = AppRoutes.Login.route
    ) {
        signInScreen(
            onNavigateToHome = {
                navController.navigateToHome()
            },
            onNavigateToSignUp = {
                navController.navigateToSignUp()
            })
        signUpScreen(
            onPopBackStack = navController::popBackStack
        )
    }
}