package com.secondbrain.ai.domain.usecase

import com.secondbrain.ai.domain.model.AuthUser
import com.secondbrain.ai.domain.repository.AuthRepository

class AuthUseCases(
    private val repository: AuthRepository
) {
    val currentUser = repository.currentUser
    suspend fun loginWithEmail(email: String, password: String): Result<AuthUser> = repository.loginWithEmail(email, password)
    suspend fun registerWithEmail(email: String, password: String): Result<AuthUser> = repository.registerWithEmail(email, password)
    suspend fun loginWithGoogle(idToken: String): Result<AuthUser> = repository.loginWithGoogle(idToken)
    suspend fun signOut(): Result<Unit> = repository.signOut()
}
