package com.example.sleephelperapp.presentation.screen.Authentication

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sleephelperapp.domain.usecase.EmailSignInUseCase
import com.example.sleephelperapp.domain.usecase.EmailSignUpUseCase
import com.example.sleephelperapp.domain.usecase.GoogleSignInUseCase
import com.example.sleephelperapp.domain.usecase.ValidateCredentialsUseCase
import com.example.sleephelperapp.domain.usecase.ValidationResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class AuthViewModel @Inject constructor(
     val googleSignInUseCase: GoogleSignInUseCase,
     val emailSignUpUseCase: EmailSignUpUseCase,
     val emailSignInUseCase: EmailSignInUseCase,
     val validateCredentialsUseCase: ValidateCredentialsUseCase,
) : ViewModel() {
    fun signInWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        FirebaseAuth.getInstance().signInWithCredential(credential)
            .addOnCompleteListener{
                if(it.isSuccessful){
                    viewModelScope.launch {
                        googleSignInUseCase(idToken)
                    }
                }else{
                    Log.d("authview model sign in using google failed" , it.exception.toString())
                }
            }

    }
    fun signUpWithEmail(email: String, password: String , confirmPassword: String) {
        if (validateCredentialsUseCase(email, password , confirmPassword) is ValidationResult.Success) {
            viewModelScope.launch {
                emailSignUpUseCase(email, password)
            }
        }else{
            TODO()//ui update
        }
    }
    fun signInWithEmail(email: String, password: String, confirmPassword: String) {
        if (validateCredentialsUseCase(email, password , confirmPassword) is ValidationResult.Success) {
        viewModelScope.launch {
            emailSignInUseCase(email, password)
        }
    }else{
        TODO()
        }
   }
}