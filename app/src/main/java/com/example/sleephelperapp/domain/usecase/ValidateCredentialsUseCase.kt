package com.example.sleephelperapp.domain.usecase

import android.util.Patterns
import javax.inject.Inject

class ValidateCredentialsUseCase @Inject constructor(){
    operator fun invoke(email: String, password: String , confirmPassword: String): ValidationResult {
        return when {
            password!=confirmPassword -> ValidationResult.Error("Password and Confirm password do not match")
            email.isBlank() -> ValidationResult.Error("Email cannot be empty")
            !isValidEmail(email) -> ValidationResult.Error("Invalid email format")
            password.isBlank() -> ValidationResult.Error("Password cannot be empty")
            password.length < 6 -> ValidationResult.Error("Password must be at least 6 characters")
            else -> ValidationResult.Success
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}

sealed class ValidationResult {
    data object Success : ValidationResult()
    data class Error(val message: String) : ValidationResult()
}