package com.secondbrain.ai.domain.repository

import com.secondbrain.ai.domain.model.AuthUser
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    val currentUser: Flow<AuthUser?>
    suspend fun loginWithEmail(email: String, password: String): Result<AuthUser>
    suspend fun registerWithEmail(email: String, password: String): Result<AuthUser>
    suspend fun loginWithGoogle(idToken: String): Result<AuthUser>
    suspend fun signOut(): Result<Unit>
}
