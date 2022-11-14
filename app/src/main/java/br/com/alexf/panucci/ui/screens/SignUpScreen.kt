package br.com.alexf.panucci.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.alexf.panucci.ui.theme.PanucciTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    onSignUpClick: () -> Unit = {}
) {
    Column(
        modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        var user by remember {
            mutableStateOf("")
        }
        var password by remember {
            mutableStateOf("")
        }
        var confirmPassword by remember {
            mutableStateOf("")
        }
        var isPasswordConfirmed by remember {
            mutableStateOf(true)
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
            },
            isError = !isPasswordConfirmed,
        )
        TextField(
            value = confirmPassword,
            onValueChange = {
                confirmPassword = it
            },
            textFieldModifier,
            visualTransformation = PasswordVisualTransformation(),
            label = {
                Text(text = "Confirm password")
            },
            isError = !isPasswordConfirmed
        )
        Button(
            onClick = {
                isPasswordConfirmed = password == confirmPassword
                if (isPasswordConfirmed) {
                    onSignUpClick()
                }
            },
            Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Sign Up")
        }
    }
}

@Preview
@Composable
fun SignUpScreenPreview() {
    PanucciTheme {
        Surface {
            SignUpScreen()
        }
    }
}