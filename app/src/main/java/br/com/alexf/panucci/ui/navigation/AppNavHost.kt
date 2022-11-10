package br.com.alexf.panucci.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import br.com.alexf.panucci.routes.AppRoute
import br.com.alexf.panucci.ui.screens.*

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: String
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        navigation(
            startDestination = AppRoute.SignIn.route,
            route = AppRoute.Login.route
        ) {
            composable(AppRoute.SignIn.route) {
                LoginScreen(
                    onSignInClick = {
                        navController.navigate(AppRoute.Home.route) {
                            popUpTo(AppRoute.Login.route)
                        }
                    },
                    onSignUpClick = {
                        navController.navigate(AppRoute.SignUp.route)
                    })
            }
            composable(AppRoute.SignUp.route) {
                SignUpScreen(onSignUpClick = {
                    navController.popBackStack()
                })
            }
        }
        navigation(
            startDestination = AppRoute.HighlightsList.route,
            route = AppRoute.Home.route
        ) {
            composable(AppRoute.HighlightsList.route) {
                HighlightsListScreen(onHighlightClick = {
                    navController.navigate(AppRoute.Checkout.route)
                })
            }
            composable(AppRoute.Menu.route) {
                MenuListScreen()
            }
            composable(AppRoute.Checkout.route) {
                CheckoutScreen()
            }
            composable(AppRoute.Drinks.route) {
                DrinksListScreen()
            }
        }

    }

}