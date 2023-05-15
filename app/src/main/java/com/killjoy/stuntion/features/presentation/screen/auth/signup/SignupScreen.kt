package com.killjoy.stuntion.features.presentation.screen.auth.signup

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.killjoy.stuntion.R
import com.killjoy.stuntion.features.data.util.Resource
import com.killjoy.stuntion.features.presentation.utils.Screen
import com.killjoy.stuntion.features.presentation.utils.components.LoadingAnimation
import com.killjoy.stuntion.features.presentation.utils.components.StuntionButton
import com.killjoy.stuntion.features.presentation.utils.components.StuntionTextField
import com.killjoy.stuntion.features.presentation.utils.components.StuntionText
import com.killjoy.stuntion.ui.theme.PrimaryBlue
import com.killjoy.stuntion.ui.theme.Type
import es.dmoral.toasty.Toasty

@Composable
fun SignupScreen(navController: NavController) {

    val viewModel = hiltViewModel<SignupViewModel>()
    val checkedState = remember { mutableStateOf(false) }
    val userResponse = viewModel.userResponse.collectAsState()
    val context = LocalContext.current
    val systemUiController = rememberSystemUiController()

    LaunchedEffect(key1 = true) {
        systemUiController.apply {
            setStatusBarColor(color = PrimaryBlue, darkIcons = true)
            setNavigationBarColor(color = Color.White, darkIcons = true)
        }
    }

    LaunchedEffect(userResponse.value) {
        when (userResponse.value) {
            is Resource.Loading -> {}
            is Resource.Error -> Toasty.error(context, userResponse.value.message.toString(), Toast.LENGTH_SHORT).show()
            is Resource.Empty -> {}
            is Resource.Success -> {
                Toasty.success(context, "Signup success!", Toast.LENGTH_SHORT).show()
                navController.navigate(Screen.LoginScreen.route) {
                    popUpTo(Screen.SignupScreen.route) {
                        inclusive = true
                    }
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PrimaryBlue)
            .verticalScroll(rememberScrollState())
    ) {
        // Login Text
        Spacer(modifier = Modifier.height(72.dp))
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            StuntionText(
                text = "Sign up",
                color = Color.White,
                textStyle = Type.displayMedium()
            )
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
                    text = "Let’s create an account first!",
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

                // Password confirm
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
                    placeHolder = "Confirm Password",
                    value = viewModel.passwordConfirmState.value,
                    onValueChange = {
                        viewModel.passwordConfirmState.value = it
                    },
                    shape = RoundedCornerShape(100.dp),
                    singleLine = true,
                    isPassword = true,
                    focusedIndicatorColor = PrimaryBlue,
                    visualTransformation = PasswordVisualTransformation(),
                    isError = viewModel.isValidConfirmPasswordState.value,
                    showWarningMessage = viewModel.isValidConfirmPasswordState.value,
                    warningMessage = "Passwords do not match",
                    modifier = Modifier.fillMaxWidth()
                )

                // Terms
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Checkbox(
                        checked = checkedState.value,
                        onCheckedChange = { checkedState.value = it },
                        colors = CheckboxDefaults.colors(
                            checkmarkColor = Color.White,
                            checkedColor = PrimaryBlue
                        )
                    )
                    StuntionText(
                        text = "I agree with the ",
                        textStyle = Type.bodySmall()
                    )
                    StuntionText(
                        text = "terms of service ",
                        color = PrimaryBlue,
                        textStyle = Type.bodySmall(),
                        modifier = Modifier.clickable { navController.navigate(Screen.SignupScreen.route) }
                    )
                    StuntionText(
                        text = "and ",
                        textStyle = Type.bodySmall()
                    )
                    StuntionText(
                        text = "privacy policy ",
                        color = PrimaryBlue,
                        textStyle = Type.bodySmall(),
                        modifier = Modifier.clickable { navController.navigate(Screen.SignupScreen.route) }
                    )
                }


                Spacer(modifier = Modifier.height(32.dp))
                if (userResponse.value is Resource.Loading)
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        LoadingAnimation()
                    }
                else {
                    // Button
                    StuntionButton(
                        onClick = {
                            viewModel.signUpUser()
                            navController.navigate(Screen.LoginScreen.route)
                        }, modifier = Modifier.fillMaxWidth()
                    ) {
                        StuntionText(
                            text = "Sign up", color = Color.White, textStyle = Type.labelLarge()
                        )
                    }

                    // Or
                    Spacer(modifier = Modifier.height(24.dp))
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Divider(
                            color = Color.Gray,
                            thickness = 1.dp,
                            modifier = Modifier.width(160.dp)
                        )
                        StuntionText(
                            text = "OR",
                            color = Color.Gray,
                            textStyle = Type.bodyLarge(),
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                        Divider(
                            color = Color.Gray,
                            thickness = 1.dp,
                            modifier = Modifier.width(160.dp)
                        )
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
                                text = "Sign up with Google", textStyle = Type.labelLarge()
                            )
                        }
                    }

                    // Already have account
                    Spacer(modifier = Modifier.height(24.dp))
                    Row(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                    ) {
                        StuntionText(
                            text = "Already have an account? ",
                            textStyle = Type.bodyMedium()
                        )
                        StuntionText(
                            text = "Log in ",
                            color = PrimaryBlue,
                            textStyle = Type.bodyMedium(),
                            modifier = Modifier.clickable { navController.navigate(Screen.LoginScreen.route) }
                        )
                    }
                }
            }
        }
    }
}