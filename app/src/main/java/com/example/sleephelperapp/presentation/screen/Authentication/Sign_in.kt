package com.example.sleephelperapp.presentation.screen.Authentication

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.navigation.NavHostController
import com.example.sleephelperapp.R
import com.example.sleephelperapp.presentation.common.*

@Composable
fun Login(navigator: NavHostController) {
    LoginScreen(navigator)
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navigator: NavHostController) {
    Scaffold(
        topBar = {
            Top_Bar(navigator,"Create Account")
        },
        containerColor = Color.Transparent
    ) { innerPadding ->Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),  // ✅ Apply innerPadding
        contentAlignment = Alignment.Center
    )
    {
        // Background Image
        Image(
            painter = painterResource(id = R.drawable.backgroundimagelogin),
            contentDescription = "Background Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 80.dp)
        ) {
            // Email Input Field
            val email = remember { mutableStateOf("Email") }
            CustomTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                leadingIcon = { null },
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
                onTextChange = { updatedText -> email.value = updatedText }
            )
            // Password Input Field
            val password = remember { mutableStateOf("Password") }
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
                onTextChange = { updatedText -> password.value = updatedText }
            )
            // Forget Password
            Forgetpassword()
            // Login Button
            CustomButton(
                text = "Login",
                fontSize = 20,
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
                    navigator.navigate("home") // Backend login process can be added here
                }
            )

            // Already Have an Account
            NonClickableText(text = "Already have an account?")
            // Sign In
            ClickableText(
                text = "Sign In",
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
    Text(
        text = text,
        modifier = modifier,
        color = color,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Italic,
        fontSize = 25.sp
    )
}

@Composable
fun NonClickableText(
    text: String = "",
    modifier: Modifier = Modifier.fillMaxWidth(),
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
fun Forgetpassword(){
    val context = LocalContext.current
    ClickableText(
        text = "Forget Password? Click here",
        modifier = Modifier
            .clickable {
                Toast.makeText(
                    context, "Check your Registered Email", Toast.LENGTH_LONG
                ).show()
            }
            .fillMaxWidth(),
        color = Color(0xFF32EF06)
    )
}

@Composable
@Preview
fun LoginScreenPreview() {
    LoginScreen(navigator = NavHostController(LocalContext.current))
}
