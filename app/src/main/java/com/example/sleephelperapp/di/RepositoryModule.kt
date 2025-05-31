package com.example.sleephelperapp.di

import com.example.sleephelperapp.data.repository.AuthRepositoryImpl
import com.example.sleephelperapp.data.repository.SleepScheduleRepositoryImpl
import com.example.sleephelperapp.domain.repository.AuthRepository
import com.example.sleephelperapp.domain.repository.SleepScheduleRepository
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
    abstract fun bindAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    @Singleton
    abstract fun bindSleepRepository(
        sleepRepositoryImpl: SleepScheduleRepositoryImpl
    ): SleepScheduleRepository
}