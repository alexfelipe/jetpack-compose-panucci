package br.com.alura.panucci

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PointOfSale
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.net.toUri
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import br.com.alura.panucci.navigation.*
import br.com.alura.panucci.ui.components.BottomAppBarItem
import br.com.alura.panucci.ui.components.PanucciBottomAppBar
import br.com.alura.panucci.ui.components.bottomAppBarItems
import br.com.alura.panucci.ui.screens.*
import br.com.alura.panucci.ui.theme.PanucciTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current
            val navController = rememberNavController()
            LaunchedEffect(Unit) {
                navController.addOnDestinationChangedListener { _, _, _ ->
                    val routes = navController.backQueue.map {
                        it.destination.route
                    }
                    Log.i("MainActivity", "onCreate: back stack - $routes")
                }
            }
            val backStackEntryState by navController.currentBackStackEntryAsState()
            val currentDestination = backStackEntryState?.destination
            PanucciTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val selectedItem by remember(currentDestination) {
                        val item = when (currentDestination?.route) {
                            menuRoute -> BottomAppBarItem.Menu
                            drinksRoute -> BottomAppBarItem.Drinks
                            else -> BottomAppBarItem.HighlightItem
                        }
                        mutableStateOf(item)
                    }
                    val containsInBottomAppBarItems =
                        when (currentDestination?.route) {
                            highlightsRoute, menuRoute, drinksRoute -> true
                            else -> false
                        }
                    val isShowFab = when (currentDestination?.route) {
                        menuRoute,
                        drinksRoute -> true
                        else -> false
                    }
                    PanucciApp(
                        bottomAppBarItemSelected = selectedItem,
                        onBottomAppBarItemSelectedChange = {
                            when (it) {
                                BottomAppBarItem.Drinks -> navController.navigateSingleTopToDrinks()
                                BottomAppBarItem.HighlightItem -> navController.navigateSingleTopToHighlightsList()
                                BottomAppBarItem.Menu -> navController.navigateSingleTopToMenuList()
                            }
                        },
                        onFabClick = {
                            Intent(
                                Intent.ACTION_VIEW,
                                uri.toUri(),
                                context,
                                MainActivity::class.java
                            ).run {
                                startActivity(this)
                            }
//                            navController.navigateToCheckout()
                        },
                        isShowTopBar = containsInBottomAppBarItems,
                        isShowBottomBar = containsInBottomAppBarItems,
                        isShowFab = isShowFab
                    ) {
                        PanucciNavHost(navController)
                    }
                }
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PanucciApp(
    bottomAppBarItemSelected: BottomAppBarItem = BottomAppBarItem.HighlightItem,
    onBottomAppBarItemSelectedChange: (BottomAppBarItem) -> Unit = {},
    onFabClick: () -> Unit = {},
    isShowTopBar: Boolean = false,
    isShowBottomBar: Boolean = false,
    isShowFab: Boolean = false,
    content: @Composable () -> Unit
) {
    Scaffold(
        topBar = {
            if (isShowTopBar) {
                CenterAlignedTopAppBar(
                    title = {
                        Text(text = "Ristorante Panucci")
                    },
                )
            }
        },
        bottomBar = {
            if (isShowBottomBar) {
                PanucciBottomAppBar(
                    item = bottomAppBarItemSelected,
                    items = bottomAppBarItems,
                    onItemChange = onBottomAppBarItemSelectedChange,
                )
            }
        },
        floatingActionButton = {
            if (isShowFab) {
                FloatingActionButton(
                    onClick = onFabClick
                ) {
                    Icon(
                        Icons.Filled.PointOfSale,
                        contentDescription = null
                    )
                }
            }
        }
    ) {
        Box(
            modifier = Modifier.padding(it)
        ) {
            content()
        }
    }
}

@Preview
@Composable
private fun PanucciAppPreview() {
    PanucciTheme {
        Surface {
            PanucciApp(
                isShowBottomBar = true,
                isShowFab = true,
                isShowTopBar = true
            ) {}
        }
    }
}