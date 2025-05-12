package com.example.sleephelperapp.presentation.screen.Authentication

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.sleephelperapp.R
import com.example.sleephelperapp.presentation.common.CustomButton
import com.example.sleephelperapp.presentation.common.CustomTextField
import com.example.sleephelperapp.presentation.common.Top_Bar

@Composable
fun Forget_password(navigator: NavHostController){

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Forget_password_Screen(navigator: NavHostController){
    Scaffold(
        topBar = {
            Top_Bar(navigator,"Reset Password")
        },
        containerColor = Color.Transparent
    ) {innerPadding ->
        Box(
            modifier  = Modifier.fillMaxSize()
                .padding(innerPadding),
                contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.backgroundimagelogin),
                contentDescription = "Background Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
//            make some logic herre
            var notClicked by remember { mutableStateOf(false) }
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 80.dp)
            ){
                val email = remember { mutableStateOf("Email") }
                if(notClicked) {
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
                    CustomButton(
                        text = "Send OTP To Mail",
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
                }else{
                    NonClickableText(text = "Please Check Your Email!")
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
                    CustomButton(
                        text = "Confirm OTP",
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
                    CustomButton(
                        text = "Resent OTP",
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
                }
            }
            }
        }
    }
@Preview
@Composable
fun prev(){
    Forget_password_Screen(NavHostController(LocalContext.current))
}