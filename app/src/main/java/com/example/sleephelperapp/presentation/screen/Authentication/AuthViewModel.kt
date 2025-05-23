package com.example.sleephelperapp.presentation.screen.Authentication

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sleephelperapp.domain.usecase.EmailSignInUseCase
import com.example.sleephelperapp.domain.usecase.EmailSignUpUseCase
import com.example.sleephelperapp.domain.usecase.GoogleSignInUseCase
import com.example.sleephelperapp.domain.usecase.ValidateCredentialsUseCase
import com.example.sleephelperapp.domain.usecase.ValidationResult
import com.example.sleephelperapp.domain.usecase.isUserSignedInUseCase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class AuthUiState {
    data object Idle : AuthUiState()
    data object Loading : AuthUiState()
    data object Success : AuthUiState()
    data class Error(val message: String) : AuthUiState()
}
/// next thing to update leather
/*
sealed class Authdata{
    data object email : Authdata()
    data object password : Authdata()
    data object confirmPassword : Authdata()
}
*/

@HiltViewModel
open class AuthViewModel @Inject constructor(
    val googleSignInUseCase: GoogleSignInUseCase,
    val emailSignUpUseCase: EmailSignUpUseCase,
    val emailSignInUseCase: EmailSignInUseCase,
    val isUserSignedInUseCase: isUserSignedInUseCase,
    val validateCredentialsUseCase: ValidateCredentialsUseCase,
) : ViewModel() {
/*    fun signInWithGoogle(idToken: String) {
        Log.d("AuthViewModel", "Attempting Google Sign-In")
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        FirebaseAuth.getInstance().signInWithCredential(credential)
            .addOnCompleteListener{
                if(it.isSuccessful){
                    Log.d("AuthViewModel", "Google Sign-In successful")
                    viewModelScope.launch {
                        googleSignInUseCase(idToken)
                    }
                }else{
                    Log.d("AuthViewModel", "Google Sign-In failed: ${it.exception}")
                }
            }
    }*/
    var authUiState = mutableStateOf<AuthUiState>(AuthUiState.Idle)
    fun signUpWithEmail(email: String, password: String , confirmPassword: String) {
        when(val result = validateCredentialsUseCase(email, password , confirmPassword)) {
            is ValidationResult.Success -> {
                authUiState.value = AuthUiState.Loading
                viewModelScope.launch {
                    try {
                        emailSignUpUseCase(email, password)
                        authUiState.value = AuthUiState.Success
                    } catch (e: Exception) {
                        authUiState.value = AuthUiState.Error(e.message ?: "Unknown error")
                    }
                }
            }
            is ValidationResult.Error -> {
                authUiState.value = AuthUiState.Error(result.message)
            }
        }
    }
    fun resetState() {
        authUiState.value = AuthUiState.Idle
    }
    fun signInWithEmail(email: String, password: String) {
        when(val result = validateCredentialsUseCase(email, password , password)){
            is ValidationResult.Success -> {
                authUiState.value = AuthUiState.Loading
                Log.d("AuthViewModel", "Email Sign-In validation successful")
                viewModelScope.launch {
                    try{
                        emailSignInUseCase(email, password)
                        authUiState.value = AuthUiState.Success
                    }catch (e : Exception){
                        authUiState.value = AuthUiState.Error(e.message ?: "Unknown error")
                    }
                }
            }
            is ValidationResult.Error -> {
                authUiState.value = AuthUiState.Error(result.message)
            }
        }
    }

    fun isUserLogedIn(): Boolean {
        Log.d("AuthViewModel", "Checking if user is logged in")
        val _isSignedIn = MutableStateFlow<Boolean?>(null)
        viewModelScope.launch {
            _isSignedIn.value = isUserSignedInUseCase()
            Log.d("AuthViewModel", "isUserSignedInUseCase returned: ${_isSignedIn.value}")
        }
        return _isSignedIn.value ?: false
    }
}
