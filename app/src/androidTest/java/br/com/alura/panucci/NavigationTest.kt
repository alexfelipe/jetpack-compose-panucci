package br.com.alura.panucci

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import br.com.alura.panucci.navigation.PanucciNavHost
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NavigationTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    lateinit var navController: TestNavHostController

    @Before
    fun setupAppNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            PanucciNavHost(navController = navController)
        }
    }

    @Test
    fun appNavHost_verifyStartDestination() {
        composeTestRule
            .onNodeWithText("Destaques do dia")
            .assertIsDisplayed()
        composeTestRule
            .onAllNodesWithContentDescription("Product Item")
            .onFirst()
            .performClick()
        MainScope().launch {
            navController.popBackStack()
        }
        composeTestRule.onAllNodesWithText("Pedir")
            .onFirst()
            .performClick()
        composeTestRule.onNodeWithText("Pedido")
            .assertIsDisplayed()
        MainScope().launch {
            navController.popBackStack()
        }
        composeTestRule
            .onNodeWithText("Destaques do dia")
            .assertIsDisplayed()
    }
}