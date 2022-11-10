package br.com.alexf.panucci.routes

sealed class AppRoute(val route: String) {
    object HighlightsList : AppRoute("Highlights")
    object Menu : AppRoute("Menu")
    object Drinks : AppRoute("Drinks")
    object Checkout : AppRoute("Checkout")
    object Login : AppRoute("Login")
    object SignIn : AppRoute("SignIn")
    object SignUp : AppRoute("SignUp")
    object Home : AppRoute("Home")
}