package com.example.sleephelperapp.presentation.screen.Authentfication

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.sleephelperapp.R
import com.example.sleephelperapp.presentation.common.*
import com.example.sleephelperapp.presentation.screen.Authentication.AuthUiState
import com.example.sleephelperapp.presentation.screen.Authentication.AuthViewModel

@Composable
fun Login(navigator: NavHostController) {
    LoginScreen(navigator , hiltViewModel())
}

@Composable
fun LoginScreen(navigator: NavHostController ,  viewModel: AuthViewModel) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val context = LocalContext.current
    var validationMessage by remember { mutableStateOf("") }
    val authState = viewModel.authUiState.value
    LaunchedEffect(authState) {
        when (authState) {
            is AuthUiState.Success -> {
                Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()
                navigator.navigate("home")
                viewModel.resetState()
            }
            is AuthUiState.Error -> {
                validationMessage = authState.message
                Toast.makeText(context, validationMessage, Toast.LENGTH_SHORT).show()
                viewModel.resetState()
            }
            else -> Unit
        }
    }
    Scaffold(
        topBar = {
            Top_Bar(navigator,"Create Account")
        },
        containerColor = Color.Transparent
    ) { innerPadding ->Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        contentAlignment = Alignment.TopStart
    )
    {
        // Background Image
        Image(
            painter = painterResource(id = R.drawable.backgroundimagelogin),
            contentDescription = "Background Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
      if(authState is AuthUiState.Loading){
           IndeterminateCircularIndicator(true)
      }else
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 80.dp)
        ) {
            // Email Input Field
            CustomTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                leadingIcon = {null},
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_email_24),
                        contentDescription = "Email Icon",
                        tint = Color.White
                    )
                },
                paddingLeadingIconEnd = 10.dp,
                paddingTrailingIconStart = 10.dp,
                text = email.value,
                placeholder = "Enter your email",
                onTextChange = { updatedText -> email.value = updatedText }
            )
            // Password Input Field
            CustomTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                leadingIcon = { null },
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_password_24),
                        contentDescription = "Password Icon",
                        tint = Color.White
                    )
                },
                paddingLeadingIconEnd = 10.dp,
                paddingTrailingIconStart = 10.dp,
                text = password.value,
                placeholder = "Enter your password",
                onTextChange = { updatedText -> password.value = updatedText }
            )
            // Forget Password
            Forgetpassword(navigator)
            // Login Button
            CustomButton(
                text = "Login",
                fontSize = 16,
                textColor = Color.White,
                buttonColor = Color.Transparent,
                transparency = 1f,
                roundedCorner = 50,
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth()
                    .fillMaxHeight(0.15f)
                    .border(1.dp, Color.Magenta, RoundedCornerShape(50)),
                onClick = {
                    viewModel.signInWithEmail(email = email.value, password = password.value)
                }
            )
            Text(
                text = "or",
                modifier = Modifier.fillMaxWidth(),
                color = Color(0xFFF8CA2F),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                fontSize = 16.sp
            )

    /*        CustomButton(
                text = "Sign in with google",
                fontSize = 20,
                textColor = Color.White,
                buttonColor = MaterialTheme.colorScheme.primary,
                transparency = 0f,
                roundedCorner = 50,
                modifier = Modifier
                    .padding(18.dp)
                    .fillMaxWidth()
                    .fillMaxHeight(0.15f) // Consider using fixed height or weight for better responsiveness
                    .border(1.dp, Color.Magenta, RoundedCornerShape(50)),
                onClick = {

                    // Validation and navigation logic
                }
            )*/
            // Already Have an Account
            NonClickableText(text = "Create a new account?", modifier = Modifier.fillMaxWidth())
            // Sign In
            ClickableText(
                text = "Sign Up",
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navigator.navigate("Register") }
            )
        }
    }
    }}
@Composable
fun ClickableText(
    text: String = " ",
    modifier: Modifier = Modifier.fillMaxWidth(),
    color: Color = Color(0xFF09D9F1),
) {
    Log.d("ClickableText", "Text: $text")
    Text(
        text = text,
        modifier = modifier,
        color = color,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Italic,
        fontSize = 18.sp
    )
}
@Composable
fun NonClickableText(
    text: String = "",
    modifier: Modifier = Modifier,
    color:Color=Color(0xFFF8CA2F)
) {
    Text(
        text = text,
        modifier = modifier,
        color =color,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Italic,
        fontSize = 20.sp
    )
}
@Composable
fun Forgetpassword(navigator: NavHostController){
    val context = LocalContext.current
    ClickableText(
        text = "Forget Password? Click here",
        modifier = Modifier
            .clickable {
                Toast.makeText(context, "Check your Registered email " + "  Email", Toast.LENGTH_LONG).show()
                navigator.navigate("ForgetPassword")
            }
            .fillMaxWidth(),
        color = Color(0xFF32EF06)
    )
}

@Composable
@Preview
fun LoginScreenPreview() {
    LoginScreen(navigator = NavHostController(LocalContext.current) , hiltViewModel())
}
