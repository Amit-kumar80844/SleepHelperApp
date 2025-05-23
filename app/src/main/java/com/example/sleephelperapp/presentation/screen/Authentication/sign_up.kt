package  com.example.sleephelperapp.presentation.screen.Authentication

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.navigation.compose.rememberNavController
import com.example.sleephelperapp.R
import com.example.sleephelperapp.presentation.common.CustomTextField
import com.example.sleephelperapp.presentation.common.CustomButton
import com.example.sleephelperapp.presentation.common.IndeterminateCircularIndicator
import com.example.sleephelperapp.presentation.common.Top_Bar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun Registration(navigator: NavHostController ) {
    val viewModel: AuthViewModel = hiltViewModel()
    RegistrationScreen(navigator = navigator , viewModel )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen(navigator: NavHostController, viewModel: AuthViewModel) { // Renamed to avoid conflict and for clarity
    val authState = viewModel.authUiState.value
    val context = LocalContext.current
    var validationMessage by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    LaunchedEffect(authState) {
        when (authState){
            is AuthUiState.Success->{
                Toast.makeText(context, "Registration Successful", Toast.LENGTH_SHORT).show()
                navigator.navigate("home")
                viewModel.resetState()
            }
            is AuthUiState.Error -> {
                validationMessage = authState.message
                Toast.makeText(context, validationMessage, Toast.LENGTH_SHORT).show()
                viewModel.resetState()
            }
            else->Unit
        }
    }
    Scaffold(
        topBar = {
            Top_Bar(navigator,"Create Account")
        },
        containerColor = Color.Transparent
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize(),
            Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.registerbackgroundimage),
                contentDescription = "Background Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
      if(authState is AuthUiState.Loading){
          IndeterminateCircularIndicator(isLoading = true)
      }else{
            // Apply innerPadding to the Column to prevent content from overlapping with TopAppBar
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(innerPadding)
                    .padding(horizontal = 0.dp, vertical = 20.dp) // Adjusted vertical padding
                    .verticalScroll(rememberScrollState()) // Added for scrollability if content overflows
            ) {
                CustomTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    leadingIcon = {  },
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
                CustomTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    leadingIcon = {  },
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
                    placeholder  = "Enter your password",
                    onTextChange = { updatedText -> password.value = updatedText }
                )
                CustomTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    leadingIcon = {  },
                    trailingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_password_24),
                            contentDescription = "Confirm Password Icon",
                            tint = Color.White
                        )
                    },
                    paddingLeadingIconEnd = 10.dp,
                    paddingTrailingIconStart = 10.dp,
                    text = confirmPassword,
                    placeholder = "Confirm your password",
                    onTextChange = { updatedText -> confirmPassword = updatedText }
                )
                CustomButton(
                    text = "Register",
                    fontSize = 20,
                    textColor = Color.White,
                    buttonColor = Color.Transparent,
                    transparency = 1f,
                    roundedCorner = 50,
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxWidth()
                        .fillMaxHeight(0.15f) // Consider using fixed height or weight for better responsiveness
                        .border(1.dp, Color.Magenta, RoundedCornerShape(50)),
                    onClick = {
                        print("Registration button clicked")
                        viewModel.signUpWithEmail(email.value, password.value, confirmPassword)
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
             /*   CustomButton(
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

                        viewModel.signInWithGoogle("idToken")
                        // Validation and navigation logic
                    }
                )*/
                Text(
                    text = "Already have an account?",
                    modifier = Modifier.fillMaxWidth(),
                    color = Color(0xFFF8CA2F),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                    fontSize = 20.sp
                )
                Text(
                    text = " Sign In ",
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            navigator.navigate("login")
                        }
                        .padding(top = 4.dp, bottom = 16.dp), // Added some padding
                    color = Color(0xFF09D9F1),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                    fontSize = 20.sp
                )
            }
        }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun RegistrationScreenPreview() {
    RegistrationScreen(
        navigator = rememberNavController(),
        hiltViewModel()
    )
}