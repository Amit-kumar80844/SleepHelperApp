package com.example.sleephelperapp.data.repository

import com.example.sleephelperapp.data.remote.auth.FirebaseAuthService
import com.example.sleephelperapp.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuthService: FirebaseAuthService
) : AuthRepository {
    override suspend fun signInWithGoogle(idToken: String): Result<Boolean> {
        return firebaseAuthService.signInWithGoogle(idToken)
    }
    override suspend fun signUpWithEmail(email: String, password: String): Result<Boolean> {
        return firebaseAuthService.signUpWithEmail(email, password)
    }

    override suspend fun signInWithEmail(email: String, password: String): Result<Boolean> {
        return firebaseAuthService.signInWithEmail(email, password)
    }

    override fun isUserSignedIn(): Boolean {
        return firebaseAuthService.isUserSignedIn()
    }
}