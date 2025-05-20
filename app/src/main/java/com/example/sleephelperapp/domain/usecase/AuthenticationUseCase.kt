package com.example.sleephelperapp.domain.usecase

import com.example.sleephelperapp.domain.repository.AuthRepository
import javax.inject.Inject

// For Google Sign-In
class GoogleSignInUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(idToken: String): Result<Boolean> {
        return repository.signInWithGoogle(idToken)
    }
}

class EmailSignUpUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String): Result<Boolean> {
        // Add validation logic here if needed
        return repository.signUpWithEmail(email, password)
    }
}

// For Email/Password Sign-In
class EmailSignInUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String): Result<Boolean> {
        return repository.signInWithEmail(email, password)

    }
}

class isUserSignedInUseCase @Inject constructor(
    private val repository: AuthRepository
){
    suspend operator fun invoke():Boolean{
        return repository.isUserSignedIn()
    }
}


