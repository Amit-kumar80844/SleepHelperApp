package com.example.sleephelperapp.domain.repository

interface AuthRepository {
    suspend fun signInWithGoogle(idToken: String): Result<Boolean>
    suspend fun signUpWithEmail(email: String, password: String): Result<Boolean>
    suspend fun signInWithEmail(email: String, password: String): Result<Boolean>
    fun isUserSignedIn(): Boolean
}