package com.example.sleephelperapp.presentation.screen.Authentication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sleephelperapp.domain.usecase.EmailSignInUseCase
import com.example.sleephelperapp.domain.usecase.EmailSignUpUseCase
import com.example.sleephelperapp.domain.usecase.GoogleSignInUseCase
import com.example.sleephelperapp.domain.usecase.ValidateCredentialsUseCase
import com.example.sleephelperapp.domain.usecase.ValidationResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val googleSignInUseCase: GoogleSignInUseCase,
    private val emailSignUpUseCase: EmailSignUpUseCase,
    private val emailSignInUseCase: EmailSignInUseCase,
    private val validateCredentialsUseCase: ValidateCredentialsUseCase
) : ViewModel() {
    fun signInWithGoogle(idToken: String) {
        viewModelScope.launch {
            googleSignInUseCase(idToken)
        }
    }
    fun signUpWithEmail(email: String, password: String) {
        if (validateCredentialsUseCase(email, password) is ValidationResult.Error) {
            viewModelScope.launch {
                emailSignUpUseCase(email, password)
            }
        }
    }
    fun signInWithEmail(email: String, password: String) {
        if (validateCredentialsUseCase(email, password) is ValidationResult.Error) {
        viewModelScope.launch {
            emailSignInUseCase(email, password)
        }
    }
        }
}