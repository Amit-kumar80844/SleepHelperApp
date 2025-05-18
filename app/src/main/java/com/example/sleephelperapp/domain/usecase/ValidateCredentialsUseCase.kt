package com.example.sleephelperapp.domain.usecase

import android.util.Patterns

class ValidateCredentialsUseCase {
    operator fun invoke(email: String, password: String): ValidationResult {
        return when {
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
    object Success : ValidationResult()
    data class Error(val message: String) : ValidationResult()
}