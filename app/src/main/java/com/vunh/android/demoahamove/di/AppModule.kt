package com.vunh.android.demoahamove.di

import android.content.Context
import com.vunh.android.demoahamove.BaseApp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
object AppModule {

    @[Singleton Provides]
    fun provideApplication(
        @ApplicationContext app: Context
    ): BaseApp = app as BaseApp

    @[Singleton Provides]
    fun provideDispatcher(): CoroutineDispatcher =
        Dispatchers.IO
}