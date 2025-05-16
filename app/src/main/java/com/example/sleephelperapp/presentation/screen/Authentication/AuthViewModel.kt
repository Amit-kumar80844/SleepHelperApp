package com.example.sleephelperapp.presentation.screen.Authentication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sleephelperapp.domain.repository.EmailSignInUseCase
import com.example.sleephelperapp.domain.repository.EmailSignUpUseCase
import com.example.sleephelperapp.domain.repository.GoogleSignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val googleSignInUseCase: GoogleSignInUseCase,
    private val emailSignUpUseCase: EmailSignUpUseCase,
    private val emailSignInUseCase: EmailSignInUseCase
) : ViewModel() {
    fun signInWithGoogle(idToken: String) {
        viewModelScope.launch {
            googleSignInUseCase(idToken)
        }
    }
    fun signUpWithEmail(email: String, password: String) {
        viewModelScope.launch {
            emailSignUpUseCase(email, password)
        }
    }
    fun signInWithEmail(email: String, password: String) {
        viewModelScope.launch {
            emailSignInUseCase(email, password)
        }
    }
}