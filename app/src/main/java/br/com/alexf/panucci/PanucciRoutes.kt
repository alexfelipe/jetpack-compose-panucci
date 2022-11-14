package br.com.alexf.panucci

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.RestaurantMenu
import androidx.compose.material.icons.outlined.LocalBar

sealed class AppRoutes(val route: String) {
    object HighlightsList : AppRoutes("highlights")
    object Menu : AppRoutes("menu")
    object Drinks : AppRoutes("drinks")
    object Checkout : AppRoutes("checkout")
    object Login : AppRoutes("login")
    object SignIn : AppRoutes("signIn")
    object SignUp : AppRoutes("signUp")
    object Home : AppRoutes("home")
}

val panucciCheckoutFabScreens = listOf(
    AppRoutes.Menu,
    AppRoutes.Drinks
)

val panucciBottomAppBarScreens = listOf(
    AppRoutes.HighlightsList to Icons.Filled.AutoAwesome,
    AppRoutes.Menu to Icons.Filled.RestaurantMenu,
    AppRoutes.Drinks to Icons.Outlined.LocalBar
)