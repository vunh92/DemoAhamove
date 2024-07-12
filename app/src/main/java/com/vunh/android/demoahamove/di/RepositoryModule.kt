package com.vunh.android.demoahamove.di

import com.vunh.android.demoahamove.data.repository.AccountRepositoryImpl
import com.vunh.android.demoahamove.domain.repository.AccountRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {
    @Binds
    fun provideAccountRepository(accountRepositoryImpl: AccountRepositoryImpl): AccountRepository
}