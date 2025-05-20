package com.example.sleephelperapp.data.remote.auth

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseAuthService @Inject constructor() {

    private val auth = Firebase.auth

    suspend fun signInWithGoogle(idToken: String): Result<Boolean> {
        return try {
            val credential =
                auth.signInWithCredential(GoogleAuthProvider.getCredential(idToken, null)).await()
            Result.success(credential.user != null)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun signUpWithEmail(email: String, password: String): Result<Boolean> {
        return try {
            val result = auth.createUserWithEmailAndPassword(email, password).await()
            Result.success(result.user != null)
        } catch (e: FirebaseAuthUserCollisionException) {
            // ❌ Email already registered
            Result.failure(Exception("An account with this email already exists"))
        } catch (e: Exception) {
            // ❌ Other error (network, weak password, etc.)
            Result.failure(e)
        }
    }

    suspend fun signInWithEmail(email: String, password: String): Result<Boolean> {
        return try {
            val result = auth.signInWithEmailAndPassword(email, password).await()
            Result.success(result.user != null)
        }catch (e: FirebaseAuthInvalidUserException) {
            // ❌ No account found with this email
            Result.failure(Exception("No account found for this email"))
        }catch (e: FirebaseAuthInvalidCredentialsException) {
            // ❌ Wrong password
            Result.failure(Exception("Incorrect password"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    suspend fun signOut() {
        auth.signOut()
    }
    fun isUserSignedIn(): Boolean = auth.currentUser != null
}
