package br.com.alexf.panucci.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.alexf.panucci.ui.theme.PanucciTheme
import br.com.alexf.panucci.ui.theme.caveatFont

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onNavigateToHome: () -> Unit = {},
    onNavigateToSignUp: () -> Unit = {}
) {
    Column(
        modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
    ) {
        val boxHeight = 300.dp
        Box(
            Modifier
                .fillMaxWidth()
                .height(boxHeight)
        ) {
            Box(
                Modifier
                    .size(boxHeight / 2)
                    .background(
                        MaterialTheme.colorScheme.secondary,
                        shape = CircleShape
                    )
                    .align(Alignment.Center)
            ) {
                Text(
                    text = "Panucci",
                    Modifier.align(Alignment.Center),
                    fontFamily = caveatFont,
                    fontSize = 36.sp,
                    color = MaterialTheme.colorScheme.onSecondary
                )
            }
        }
        var user by remember {
            mutableStateOf("")
        }
        var password by remember {
            mutableStateOf("")
        }
        val textFieldModifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
        TextField(
            value = user,
            onValueChange = {
                user = it
            },
            textFieldModifier,
            label = {
                Text(text = "User")
            }
        )
        TextField(
            value = password,
            onValueChange = {
                password = it
            },
            textFieldModifier,
            visualTransformation = PasswordVisualTransformation(),
            label = {
                Text(text = "Password")
            }
        )
        val buttonModifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
        Button(onClick = onNavigateToHome, buttonModifier) {
            Text(text = "Sign In")
        }
        TextButton(onClick = onNavigateToSignUp, buttonModifier) {
            Text(text = "Sign Up")
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    PanucciTheme {
        Surface {
            LoginScreen()
        }
    }
}