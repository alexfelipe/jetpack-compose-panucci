package br.com.alexf.panucci.routes

sealed class AppRoute(val route: String) {
    object HighlightsList : AppRoute("Destaques")
    object Menu : AppRoute("Menu")
    object Drinks : AppRoute("Bebidas")
    object Checkout : AppRoute("Pedido")
}