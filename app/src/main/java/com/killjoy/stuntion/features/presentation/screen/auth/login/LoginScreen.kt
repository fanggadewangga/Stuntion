package com.killjoy.stuntion.features.presentation.screen.auth.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.killjoy.stuntion.R
import com.killjoy.stuntion.features.presentation.utils.Screen
import com.killjoy.stuntion.features.presentation.utils.components.StuntionButton
import com.killjoy.stuntion.ui.stuntionUI.StuntionText
import com.killjoy.stuntion.features.presentation.utils.components.StuntionTextField
import com.killjoy.stuntion.ui.theme.PrimaryBlue
import com.killjoy.stuntion.ui.theme.Type

@Composable
fun LoginScreen(navController: NavController) {

    val viewModel = hiltViewModel<LoginViewModel>()
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PrimaryBlue)
    ) {
        // Login Text
        Spacer(modifier = Modifier.height(72.dp))
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            StuntionText(text = "Log in", color = Color.White, textStyle = Type.displayMedium())
        }

        // Box
        Spacer(modifier = Modifier.height(32.dp))
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Color.White, RoundedCornerShape(topStart = 48.dp, topEnd = 48.dp)
                )
                .padding(horizontal = 16.dp, vertical = 24.dp)
        ) {
            Column(modifier = Modifier.matchParentSize()) {

                // Welcome to Stuntion
                Row(
                    Modifier
                        .fillMaxWidth()
                        .height(56.dp), verticalAlignment = Alignment.Bottom
                ) {
                    StuntionText(text = "Welcome to", textStyle = Type.headlineLarge())
                    Spacer(modifier = Modifier.width(8.dp))
                    Image(
                        painter = painterResource(id = R.drawable.ic_app_text_logo),
                        contentDescription = "App Text Logo",
                        modifier = Modifier
                            .padding(bottom = 6.dp)
                            .height(32.dp)
                    )
                }

                // Welcome back
                StuntionText(
                    text = "Welcome back! Pelase enter your details.",
                    textStyle = Type.bodyMedium(),
                    color = Color.Gray
                )

                // Email
                Spacer(modifier = Modifier.height(32.dp))
                StuntionTextField(
                    placeHolder = "Email",
                    value = viewModel.emailState.value,
                    onValueChange = {
                        viewModel.emailState.value = it
                    },
                    shape = RoundedCornerShape(100.dp),
                    singleLine = true,
                    focusedIndicatorColor = PrimaryBlue,
                    isError = viewModel.isValidEmailState.value,
                    showWarningMessage = viewModel.isValidEmailState.value,
                    warningMessage = "Invalid email format",
                    modifier = Modifier.fillMaxWidth()
                )

                // Password
                Spacer(modifier = Modifier.height(16.dp))
                StuntionTextField(
                    trailingIcon = {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription = "Password icon"
                            )
                        }
                    },
                    placeHolder = "Password",
                    value = viewModel.passwordState.value,
                    onValueChange = {
                        viewModel.passwordState.value = it
                    },
                    shape = RoundedCornerShape(100.dp),
                    singleLine = true,
                    isPassword = true,
                    focusedIndicatorColor = PrimaryBlue,
                    visualTransformation = PasswordVisualTransformation(),
                    isError = viewModel.isValidPasswordState.value,
                    showWarningMessage = viewModel.isValidPasswordState.value,
                    warningMessage = "Password must be longer than 6 characters",
                    modifier = Modifier.fillMaxWidth()
                )

                // Forget Password
                Spacer(modifier = Modifier.height(8.dp))
                StuntionText(
                    text = "Forget password?",
                    textStyle = Type.titleSmall(),
                    color = PrimaryBlue,
                    modifier = Modifier.align(Alignment.End)
                )

                // Button
                Spacer(modifier = Modifier.height(32.dp))
                StuntionButton(
                    onClick = {
                        viewModel.login()
                    }, modifier = Modifier.fillMaxWidth()
                ) {
                    StuntionText(
                        text = "Log in", color = Color.White, textStyle = Type.labelLarge()
                    )
                }

                // Or
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Divider(color = Color.Gray, thickness = 1.dp, modifier = Modifier.width(160.dp))
                    StuntionText(
                        text = "OR",
                        color = Color.Gray,
                        textStyle = Type.bodyLarge(),
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    Divider(color = Color.Gray, thickness = 1.dp, modifier = Modifier.width(160.dp))
                }

                // Google Button
                Spacer(modifier = Modifier.height(24.dp))
                StuntionButton(
                    onClick = { /*TODO*/ },
                    backgroundColor = Color.White,
                    borderColor = Color.Gray,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row {
                        Image(
                            painter = painterResource(id = R.drawable.ic_google),
                            contentDescription = "Google Icon"
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        StuntionText(
                            text = "Log in with Google", textStyle = Type.labelLarge()
                        )
                    }
                }

                // Already have account
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    modifier = Modifier
                        .align(CenterHorizontally)
                ) {
                    StuntionText(text = "Don’t have an account? ", textStyle = Type.bodyMedium())
                    StuntionText(
                        text = "Sign up ",
                        color = PrimaryBlue,
                        textStyle = Type.bodyMedium(),
                        modifier = Modifier.clickable { navController.navigate(Screen.SignupScreen.route) }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun LoginPrev() {
    LoginScreen(navController = rememberNavController())
}