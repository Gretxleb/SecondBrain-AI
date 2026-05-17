package com.secondbrain.ai.data.di

import com.secondbrain.ai.data.repository.*
import com.secondbrain.ai.domain.repository.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindAuthRepository(repository: AuthRepositoryImpl): AuthRepository

    @Binds
    @Singleton
    abstract fun bindNotesRepository(repository: NotesRepositoryImpl): NotesRepository

    @Binds
    @Singleton
    abstract fun bindAIRepository(repository: AIRepositoryImpl): AIRepository

    @Binds
    @Singleton
    abstract fun bindSearchRepository(repository: SearchRepositoryImpl): SearchRepository

    @Binds
    @Singleton
    abstract fun bindSettingsRepository(repository: SettingsRepositoryImpl): SettingsRepository

    @Binds
    @Singleton
    abstract fun bindVoiceRepository(repository: VoiceRepositoryImpl): VoiceRepository

    @Binds
    @Singleton
    abstract fun bindRemindersRepository(repository: RemindersRepositoryImpl): RemindersRepository
}
