package com.example.sleephelperapp.di

import com.example.sleephelperapp.data.repository.AuthRepositoryImpl // Assuming this is your implementation
import com.example.sleephelperapp.domain.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) // Or another appropriate component
abstract class RepositoryModule {

    @Binds
    @Singleton // If AuthRepository should be a singleton
    abstract fun bindAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository
}