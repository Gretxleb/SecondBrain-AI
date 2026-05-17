package com.secondbrain.ai.domain.di

import com.secondbrain.ai.domain.repository.*
import com.secondbrain.ai.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {
    @Provides
    @Singleton
    fun provideAuthUseCases(authRepository: AuthRepository): AuthUseCases = AuthUseCases(authRepository)

    @Provides
    @Singleton
    fun provideNotesUseCases(notesRepository: NotesRepository): NotesUseCases = NotesUseCases(notesRepository)

    @Provides
    @Singleton
    fun provideAiUseCases(aiRepository: AIRepository): AiUseCases = AiUseCases(aiRepository)

    @Provides
    @Singleton
    fun provideSearchUseCases(searchRepository: SearchRepository): SearchUseCases = SearchUseCases(searchRepository)

    @Provides
    @Singleton
    fun provideSettingsUseCases(settingsRepository: SettingsRepository): SettingsUseCases = SettingsUseCases(settingsRepository)

    @Provides
    @Singleton
    fun provideVoiceUseCases(voiceRepository: VoiceRepository): VoiceUseCases = VoiceUseCases(voiceRepository)

    @Provides
    @Singleton
    fun provideRemindersUseCases(remindersRepository: RemindersRepository): RemindersUseCases = RemindersUseCases(remindersRepository)
}
