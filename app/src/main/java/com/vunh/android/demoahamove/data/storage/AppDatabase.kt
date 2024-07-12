package com.vunh.android.demoahamove.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vunh.android.demoahamove.domain.entity.AccountEntity
import com.vunh.android.demoahamove.domain.entity.PopularEntity

@Database(entities = [AccountEntity::class, PopularEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract val accountDao: AccountDao
    abstract val popularDao: PopularDao
}