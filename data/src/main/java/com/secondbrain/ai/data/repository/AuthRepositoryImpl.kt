package com.secondbrain.ai.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.secondbrain.ai.domain.model.AuthUser
import com.secondbrain.ai.domain.repository.AuthRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {

    override val currentUser: Flow<AuthUser?> = callbackFlow {
        val authStateListener = FirebaseAuth.AuthStateListener { auth ->
            val user = auth.currentUser
            trySend(user?.let { AuthUser(it.uid, it.email) })
        }
        firebaseAuth.addAuthStateListener(authStateListener)
        awaitClose { firebaseAuth.removeAuthStateListener(authStateListener) }
    }

    override suspend fun loginWithEmail(email: String, password: String): Result<AuthUser> {
        return try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            result.user?.let { Result.success(AuthUser(it.uid, it.email)) }
                ?: Result.failure(Exception("Authentication failed"))
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }

    override suspend fun registerWithEmail(email: String, password: String): Result<AuthUser> {
        return try {
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            result.user?.let { Result.success(AuthUser(it.uid, it.email)) }
                ?: Result.failure(Exception("Registration failed"))
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }

    override suspend fun loginWithGoogle(idToken: String): Result<AuthUser> {
        return try {
            val credential = GoogleAuthProvider.getCredential(idToken, null)
            val result = firebaseAuth.signInWithCredential(credential).await()
            result.user?.let { Result.success(AuthUser(it.uid, it.email)) }
                ?: Result.failure(Exception("Authentication failed"))
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }

    override suspend fun signOut(): Result<Unit> {
        return try {
            firebaseAuth.signOut()
            Result.success(Unit)
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }
}
