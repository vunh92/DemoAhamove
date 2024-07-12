package com.vunh.android.demoahamove.di

import androidx.room.Room
import com.vunh.android.demoahamove.BaseApp
import com.vunh.android.demoahamove.data.storage.AccountDao
import com.vunh.android.demoahamove.data.storage.AppDatabase
import com.vunh.android.demoahamove.data.storage.PopularDao
import com.vunh.android.demoahamove.utils.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @[Singleton Provides]
    fun provideAppDatabase(app: BaseApp) : AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            Constant.DATABASE_NAME,
        )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @[Singleton Provides]
    fun provideAccountDao(appDatabase: AppDatabase) : AccountDao {
        return appDatabase.accountDao
    }

    @[Singleton Provides]
    fun provideReposDao(appDatabase: AppDatabase) : PopularDao {
        return appDatabase.popularDao
    }
}